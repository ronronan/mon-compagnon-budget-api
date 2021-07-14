package fr.rmorel.moncompagnonbudgetapi.app.bank

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/bank-account")
class BankAccountController(private val bankAccountService: BankAccountService) {

    @GetMapping("/{userId}")
    fun findAccountsByUser(@PathVariable userId: Long): ResponseEntity<List<BankAccountDto>> {
        val listAccount = bankAccountService.findByUserId(userId)
        return if (listAccount.isEmpty()){
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(listAccount)
        } else {
            ResponseEntity.ok(listAccount)
        }
    }

    @PostMapping("/{userId}")
    fun createBankAccount(@PathVariable userId: Long, @RequestBody dto: BankAccountDto): ResponseEntity<Void> {
        return if (bankAccountService.createAccount(dto, userId)) {
            ResponseEntity.ok(null)
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PutMapping("/amount")
    fun updateAmountOfBankAccount(@RequestBody dto: BankAccountDto): ResponseEntity<Void> {
        return if (bankAccountService.updateAmountAccount(dto.id, dto.amount)) {
            ResponseEntity.ok(null)
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }
}