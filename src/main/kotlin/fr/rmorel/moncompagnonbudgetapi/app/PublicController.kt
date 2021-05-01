package fr.rmorel.moncompagnonbudgetapi.app

import fr.rmorel.moncompagnonbudgetapi.app.user.UserEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalDateTime

@RestController
@RequestMapping("")
class PublicController {

    @GetMapping("/api/v1/public")
    fun testPublic( @RequestParam(required = false) title: String?): ResponseEntity<UserEntity> = ResponseEntity(
            UserEntity(1, "admin@test.fr", "Admin", "Test", LocalDate.now(), LocalDateTime.now(), "ADMIN"),
            HttpStatus.OK
        )

}