package fr.rmorel.moncompagnonbudgetapi.app.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): List<UserEntity>
}