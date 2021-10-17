package fr.rmorel.moncompagnonbudgetapi.app.category

import javax.persistence.*

@Entity
@Table(name = "categories")
class CategoryEntity (
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    @Column val name: String,
    @Column val color: String,
    @Column val icon: String,
    @Column val parentId: Long
) {

    companion object {
        fun fromDto(dto: CategoryDto): CategoryEntity {
            return CategoryEntity(
                id = dto.id,
                name = dto.name,
                color = dto.color,
                icon = dto.icon,
                parentId = dto.parentId
            )
        }
    }

    override fun toString(): String = "Category[id=$id, name=$name, color=$color, icon=$icon, parentId=$parentId]"
}