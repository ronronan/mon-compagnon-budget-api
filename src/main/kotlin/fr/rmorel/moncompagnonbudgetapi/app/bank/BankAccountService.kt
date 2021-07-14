package fr.rmorel.moncompagnonbudgetapi.app.bank

import org.springframework.stereotype.Service

@Service
class BankAccountService(private val bankAccountRepository: BankAccountRepository ) {

    fun findByUserId(userId: Long): List<BankAccountDto> = this.bankAccountRepository.findByUserId(userId).map { BankAccountDto.fromEntity(it) }

    fun createAccount(bankAccountDto: BankAccountDto, userId: Long): Boolean {
        val entity = BankAccountEntity.fromDto(bankAccountDto, userId)
        val result = this.bankAccountRepository.save(entity)
        return result.id > 0;
    }

    fun updateAmountAccount(id: Long, amount: Float): Boolean {
        val dbEntity = this.bankAccountRepository.findById(id)

        return if (dbEntity.isPresent) {
            val entity = dbEntity.get()
            entity.updateAmount(amount)
            this.bankAccountRepository.save(entity).id == id
        } else {
            false
        }
    }
}