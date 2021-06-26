package fr.rmorel.moncompagnonbudgetapi.app.test

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController
@RequestMapping("/api/v1/test")
class TestController(
    private val testRepository: TestRepository
) {
    private val logger = LoggerFactory.getLogger(TestController::class.java)

    @GetMapping("")
    fun getAll( @RequestParam(required = false) title: String?): ResponseEntity<List<TestEntity>> {

        return try {
            val testList = ArrayList<TestEntity>()
            if (title == null) {
                this.testRepository.findAll().forEach(testList::add)
            } else {
                this.testRepository.findByTitleContaining(title).forEach(testList::add)
            }

            if (testList.isEmpty()) {
                ResponseEntity(HttpStatus.NO_CONTENT)
            } else {
                ResponseEntity(testList, HttpStatus.OK)
            }
        } catch (e: Exception){
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<TestEntity> {
        val testEntity = this.testRepository.findById(id)
        return if (testEntity.isPresent) {
            ResponseEntity(testEntity.get(), HttpStatus.OK)
        }else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun create(@RequestBody testEntityInput: TestEntity): ResponseEntity<TestEntity> {
        return try {
            val testEntityCreated = this.testRepository.save(
                TestEntity(title = testEntityInput.title, description = testEntityInput.description, published = testEntityInput.published)
            )
            ResponseEntity(testEntityCreated, HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody testEntityInput: TestEntity): ResponseEntity<TestEntity> {
        val testEntity = this.testRepository.findById(id)

        return if (testEntity.isPresent) {
            val testEntityLocal = testEntity.get()
            testEntityLocal.title = testEntityInput.title
            testEntityLocal.description = testEntityInput.description
            testEntityLocal.published = testEntityInput.published
            val testEntityUpdated = this.testRepository.save(testEntityLocal)
            ResponseEntity(testEntityUpdated, HttpStatus.OK)
        } else{
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: Long): ResponseEntity<HttpStatus> {
        return try {
            this.testRepository.deleteById(id)
            ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/all")
    fun deleteAll(): ResponseEntity<HttpStatus> {
        return try {
            this.testRepository.deleteAll()
            ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/published")
    fun getByPublished(): ResponseEntity<List<TestEntity>> {
        return try {
            val testEntityList = this.testRepository.findByPublished(true)
            if (testEntityList.isEmpty()) {
                ResponseEntity(HttpStatus.NO_CONTENT)
            } else {
                ResponseEntity(testEntityList, HttpStatus.OK)
            }
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}