package fr.rmorel.moncompagnonbudgetapi.app.category

class CategoryDto (
    val id: Long = 0,
    val name: String,
    val color: String,
    val icon: String,
    val parentId: Long
) {
    companion object {
        fun fromEntity(entity: CategoryEntity): CategoryDto {
            return CategoryDto(
                entity.id,
                entity.name,
                entity.color,
                entity.icon,
                entity.parentId,
            )
        }
    }

    override fun toString(): String = "CategoryDto(id=$id, name='$name', color='$color', icon='$icon', parentId=$parentId)"
}