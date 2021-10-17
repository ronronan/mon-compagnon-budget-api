package fr.rmorel.moncompagnonbudgetapi.app.category

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/category")
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping("")
    fun findAll(): ResponseEntity<List<CategoryDto>> {
        val listUser = categoryService.findAll()
        return if (listUser.isEmpty()){
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(listUser)
        } else {
            ResponseEntity.ok(listUser)
        }
    }

    @PostMapping("")
    fun addOne(@RequestBody categoryDto: CategoryDto): ResponseEntity<CategoryDto> = ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addOne(categoryDto))
}