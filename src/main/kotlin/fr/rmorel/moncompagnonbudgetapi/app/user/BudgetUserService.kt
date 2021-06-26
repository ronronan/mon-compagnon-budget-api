package fr.rmorel.moncompagnonbudgetapi.app.user

import org.keycloak.representations.AccessToken
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BudgetUserService(private val budgetUserRepository: BudgetUserRepository) {

    private val logger = LoggerFactory.getLogger(BudgetUserService::class.java)

    fun checkIfUserExistOrCreate(accessToken: AccessToken) {
        val entity = BudgetUserEntity.fromAccessToken(accessToken)
        logger.info("{}", entity)
    }
}