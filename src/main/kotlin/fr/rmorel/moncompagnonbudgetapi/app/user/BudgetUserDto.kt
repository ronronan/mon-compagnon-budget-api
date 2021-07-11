package fr.rmorel.moncompagnonbudgetapi.app.user

import java.time.LocalDate
import java.time.LocalDateTime

class BudgetUserDto(
    val id: Long,
    val username: String,
    val email: String,
    val firstname: String?,
    val lastname: String?,
    val createdAt: LocalDate,
    val lastConnection: LocalDateTime,
    val picture: String?,
    val role: BudgetUserRole
) {

    companion object {
        fun fromEntity(entity: BudgetUserEntity): BudgetUserDto {
            val profileImage = entity.picture ?: "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png"

            return BudgetUserDto(
                entity.id,
                entity.username,
                entity.email,
                entity.firstname,
                entity.lastname,
                entity.createdAt,
                entity.lastConnection,
                profileImage,
                entity.role
            )
        }
    }

    override fun toString(): String {
        return "BudgetUserDto(id=$id, username='$username', email='$email', firstname=$firstname, lastname=$lastname, createdAt=$createdAt, lastConnection=$lastConnection, picture=$picture, role=$role)"
    }
}