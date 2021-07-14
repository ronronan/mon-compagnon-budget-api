package fr.rmorel.moncompagnonbudgetapi.app.bank

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name= "bank_account")
class BankAccountEntity (
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column val name: String,
    @Column val bankName: String,
    @Column var amount: Float,
    @Column val createdAt: LocalDate,
    @Column var updatedAt: LocalDateTime,
    @Column val userId: Long,
    @Version val version: Int = 0
) {

    companion object {
        fun fromDto(dto: BankAccountDto, userId: Long): BankAccountEntity {
            return BankAccountEntity(
                name = dto.name,
                bankName = dto.bankName,
                amount = dto.amount,
                createdAt = LocalDate.now(),
                updatedAt = LocalDateTime.now(),
                userId = userId
            )
        }
    }

    fun updateAmount(amount: Float) {
        this.amount = amount
        this.updatedAt = LocalDateTime.now()
    }

    override fun toString(): String {
        return "BankAccountEntity(id=$id, name='$name', bankName='$bankName', amount=$amount, createdAt=$createdAt, updatedAt=$updatedAt, version=$version)"
    }
}