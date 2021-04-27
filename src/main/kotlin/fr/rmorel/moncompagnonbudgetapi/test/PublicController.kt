package fr.rmorel.moncompagnonbudgetapi.test

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception


@CrossOrigin("*")
@RestController
@RequestMapping("/api/public")
class PublicController {

    @GetMapping("")
    fun version(): ResponseEntity<String> = ResponseEntity("0.0.1", HttpStatus.OK)

}