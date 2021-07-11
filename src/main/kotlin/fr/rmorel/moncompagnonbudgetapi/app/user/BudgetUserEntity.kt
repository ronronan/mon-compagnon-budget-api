package fr.rmorel.moncompagnonbudgetapi.app.user

import org.keycloak.representations.AccessToken
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

enum class BudgetUserRole {
    ADMIN, USER;

    companion object {
        fun fromString(role: String?): BudgetUserRole {
            return if("ADMIN" == role) {
                ADMIN
            } else {
                USER
            }
        }
    }
}

@Entity
@Table(name= "budget_users")
class BudgetUserEntity (
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column val username: String,
    @Column val email: String,
    @Column val firstname: String?,
    @Column val lastname: String?,
    @Column val picture: String?,
    @Column val createdAt: LocalDate,
    @Column var lastConnection: LocalDateTime,
    @Enumerated @Column val role: BudgetUserRole
) {
    override fun toString() = "User[id=$id, username=$username, email=$email, firstname=$firstname, lastname=$lastname, createdAt=$createdAt, lastConnection=$lastConnection, picture=$picture, role=$role]"

   companion object {
       fun fromAccessToken(accessToken: AccessToken): BudgetUserEntity {
           return BudgetUserEntity(
               email = accessToken.email,
               username = accessToken.preferredUsername,
               firstname = accessToken.givenName,
               lastname = accessToken.familyName,
               createdAt = LocalDate.now(),
               lastConnection = LocalDateTime.now(),
               picture = null,
               role = BudgetUserRole.fromString(accessToken.resourceAccess["monbudget-api"]?.roles?.firstOrNull())
           )
       }
   }

    fun updateLastConnection() {
        this.lastConnection = LocalDateTime.now()
    }
}