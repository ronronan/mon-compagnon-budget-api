package fr.rmorel.moncompagnonbudgetapi.app.user

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.keycloak.representations.AccessToken
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1/user")
class BudgetUserController(
    private val budgetUserService: BudgetUserService
) {
    @PostMapping("/check")
    fun checkIfUserExistOrCreate(request: HttpServletRequest) : ResponseEntity<BudgetUserDto> {
        val principal = request.userPrincipal as KeycloakAuthenticationToken
        val accessToken = principal.account.keycloakSecurityContext.token as AccessToken
        return ResponseEntity.ok(
            budgetUserService.checkIfUserExistOrCreate(accessToken)
        )
    }

    @GetMapping("")
    fun findAll(): ResponseEntity<List<BudgetUserDto>> {
        val listUser = budgetUserService.findAll()
        return if (listUser.isEmpty()){
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(listUser)
        } else {
            ResponseEntity.ok(listUser)
        }
    }
}