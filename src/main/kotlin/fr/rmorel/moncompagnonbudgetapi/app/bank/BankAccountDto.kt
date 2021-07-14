package fr.rmorel.moncompagnonbudgetapi.app.bank

import java.time.LocalDate
import java.time.LocalDateTime

class BankAccountDto(
    val id: Long = 0,
    val name: String,
    val bankName: String,
    val amount: Float,
    val createdAt: LocalDate?,
    val updatedAt: LocalDateTime?
) {

    companion object {
        fun fromEntity(entity: BankAccountEntity): BankAccountDto {
            return BankAccountDto(
                entity.id,
                entity.name,
                entity.bankName,
                entity.amount,
                entity.createdAt,
                entity.updatedAt
            )
        }
    }

    override fun toString(): String {
        return "BankAccountDto(id=$id, name='$name', bankName='$bankName', amount=$amount, createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}