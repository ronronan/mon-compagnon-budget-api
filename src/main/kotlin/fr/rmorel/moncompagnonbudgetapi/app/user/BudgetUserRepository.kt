package fr.rmorel.moncompagnonbudgetapi.app.user

import org.springframework.data.jpa.repository.JpaRepository

interface BudgetUserRepository : JpaRepository<BudgetUserEntity, Long> {
    fun findByEmail(email: String): List<BudgetUserEntity>
}