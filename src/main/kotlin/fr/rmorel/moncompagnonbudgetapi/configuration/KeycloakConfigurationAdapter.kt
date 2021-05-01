package fr.rmorel.moncompagnonbudgetapi.configuration

import org.keycloak.adapters.KeycloakConfigResolver
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
class KeycloakConfigurationAdapter: KeycloakWebSecurityConfigurerAdapter() {

    @Bean
    override fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy {
        return RegisterSessionAuthenticationStrategy(SessionRegistryImpl())
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        val keycloakAuthenticationProvider = keycloakAuthenticationProvider()
        // simple Authority Mapper to avoid ROLE_
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(SimpleAuthorityMapper())
        auth.authenticationProvider(keycloakAuthenticationProvider)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        super.configure(http)
        http
            .logout().addLogoutHandler(keycloakLogoutHandler())
            .logoutUrl("/logout").logoutSuccessHandler { _: HttpServletRequest, response: HttpServletResponse, _: Authentication -> response.status = HttpServletResponse.SC_OK }
            // add cors options
            .and().cors()
            // disable csrf because of API mode
            .and().csrf().disable()
            // active Stateless service option
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            // manage routes securisation here
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .antMatchers("/logout", "/", "/api", "/api/v1", "/api/v1/public", "/api/v1/public/**").permitAll()
            .antMatchers("/api/v1/admin", "/api/v1/admin/**").hasRole("ADMIN")
            .antMatchers("/api/v1/**").authenticated()
            .anyRequest().denyAll()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf(HttpMethod.OPTIONS.name, HttpMethod.GET.name, HttpMethod.POST.name, HttpMethod.PUT.name, HttpMethod.DELETE.name)
        configuration.allowedHeaders = listOf("Access-Control-Allow-Headers", "Access-Control-Allow-Origin", "Authorization")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}