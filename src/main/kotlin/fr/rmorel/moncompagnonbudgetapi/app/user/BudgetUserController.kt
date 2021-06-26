package fr.rmorel.moncompagnonbudgetapi.app.user

import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.keycloak.representations.AccessToken
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1/user")
class BudgetUserController(
    private val budgetUserService: BudgetUserService
) {
    private val logger = LoggerFactory.getLogger(BudgetUserController::class.java)

    @PostMapping("/check")
    fun checkIfUserExistOrCreate(request: HttpServletRequest) : ResponseEntity<BudgetUserDto> {
        val principal = request.userPrincipal as KeycloakAuthenticationToken
        val accessToken = principal.account.keycloakSecurityContext.token as AccessToken
        budgetUserService.checkIfUserExistOrCreate(accessToken)
        return ResponseEntity.ok(BudgetUserDto("rm@test.fr"))
    }
}