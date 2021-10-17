package fr.rmorel.moncompagnonbudgetapi.app.category

import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    fun findAll(): List<CategoryDto> = categoryRepository.findAll().map { CategoryDto.fromEntity(it) }

    fun addOne(categoryDto: CategoryDto): CategoryDto {
        val result = categoryRepository.save(CategoryEntity.fromDto(categoryDto))
        return CategoryDto.fromEntity(result)
    }

}