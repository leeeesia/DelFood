package t.project.delfood.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import t.project.delfood.dto.Dish

@Entity
data class DishEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val image: String?,
    val content: String?,
) {

    fun toDto() = Dish(id,  name, image, content)


    companion object {
        fun fromDto(dto: Dish) =
            DishEntity(
                dto.id,
                dto.name,
                dto.image,
                dto.content,
            )

    }
}