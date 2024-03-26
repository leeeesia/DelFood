package t.project.delfood.dto

import com.google.gson.annotations.SerializedName

data class Dish(
    val id: Int,
    val name: String,
    val image: String?,
    val content: String?
)

data class DishResponse(
    val searchResults: ArrayList<SearchResult>
)
data class SearchResult(
    val results: ArrayList<Dish>
)


