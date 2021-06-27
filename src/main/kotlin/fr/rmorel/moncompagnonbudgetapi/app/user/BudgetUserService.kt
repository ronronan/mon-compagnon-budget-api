package fr.rmorel.moncompagnonbudgetapi.app.user

import org.keycloak.representations.AccessToken
import org.springframework.stereotype.Service

@Service
class BudgetUserService(private val budgetUserRepository: BudgetUserRepository) {

    fun checkIfUserExistOrCreate(accessToken: AccessToken): BudgetUserDto {
        val entity = BudgetUserEntity.fromAccessToken(accessToken)
        val dbEntity = budgetUserRepository.findByEmail(entity.email)

        // User doesn't exist, creation of user
        val result = if (dbEntity == null) {
            budgetUserRepository.save(entity)
        } else { // User exist, return existing
            dbEntity.updateLastConnection()
            budgetUserRepository.save(dbEntity)
        }
        return BudgetUserDto.fromEntity(result)
    }

    fun findAll(): List<BudgetUserDto> = budgetUserRepository.findAll().map { BudgetUserDto.fromEntity(it) }
}