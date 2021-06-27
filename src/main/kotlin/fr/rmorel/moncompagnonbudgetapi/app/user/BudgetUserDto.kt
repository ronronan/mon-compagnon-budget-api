package fr.rmorel.moncompagnonbudgetapi.app.user

import java.time.LocalDate
import java.time.LocalDateTime

class BudgetUserDto(
    val id: Long,
    val email: String,
    val firstname: String?,
    val lastname: String?,
    val createdAt: LocalDate,
    val lastConnection: LocalDateTime,
    val role: BudgetUserRole
) {

    companion object {
        fun fromEntity(entity: BudgetUserEntity): BudgetUserDto {
            return BudgetUserDto(
                entity.id,
                entity.email,
                entity.firstname,
                entity.lastname,
                entity.createdAt,
                entity.lastConnection,
                entity.role
            )
        }
    }
}