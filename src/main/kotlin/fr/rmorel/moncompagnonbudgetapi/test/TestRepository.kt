package fr.rmorel.moncompagnonbudgetapi.test

import org.springframework.data.jpa.repository.JpaRepository

interface TestRepository : JpaRepository<TestEntity, Long> {
    fun findByPublished(published: Boolean): List<TestEntity>

    fun findByTitleContaining(title: String): List<TestEntity>
}