package fr.rmorel.moncompagnonbudgetapi.user

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name= "users")
class UserEntity (
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column val email: String,
    @Column val firstname: String,
    @Column val lastname: String,
    @Column val createdAt: LocalDate,
    @Column val lastConnection: LocalDateTime,
    @Column val role: String
) {
    override fun toString() = "User[id=$id, email=$email, firstname=$firstname, lastname=$lastname, createdAt=$createdAt, lastConnection=$lastConnection, role=$role]"
}