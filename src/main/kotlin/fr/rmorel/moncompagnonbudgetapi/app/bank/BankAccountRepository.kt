package fr.rmorel.moncompagnonbudgetapi.app.bank

import org.springframework.data.jpa.repository.JpaRepository

interface BankAccountRepository : JpaRepository<BankAccountEntity, Long> {
    fun findByUserId(userId: Long): List<BankAccountEntity>
}
