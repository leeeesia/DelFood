package t.project.delfood.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import t.project.delfood.dto.Dish


interface DishRepository {
    val data: Flow<List<Dish>>
    suspend fun getAll()
}
