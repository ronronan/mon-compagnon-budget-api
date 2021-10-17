package fr.rmorel.moncompagnonbudgetapi.app.category

import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<CategoryEntity, Long> {
}