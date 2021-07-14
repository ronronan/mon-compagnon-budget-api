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
    @Column val amount: Float,
    @Column val createdAt: LocalDate,
    @Column var updatedAt: LocalDateTime
) {

    override fun toString(): String {
        return "BankAccountEntity(id=$id, name='$name', bankName='$bankName', amount=$amount, createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}