package t.project.delfood.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.awaitResponse
import t.project.delfood.api.DishApiService
import t.project.delfood.dao.DishDao
import t.project.delfood.dto.Dish
import t.project.delfood.entity.DishEntity
import javax.inject.Inject

class DishRepositoryImpl @Inject constructor(
    private val dao: DishDao,
    private val apiService: DishApiService,
    ) : DishRepository {


    override val data: Flow<List<Dish>> = dao.getAll().map {
        it.map(DishEntity::toDto)
    }


    override suspend fun getAll() {
        val response = apiService.getDishByCategory().awaitResponse()
        if (!response.isSuccessful) {
            throw error("Response code is ${response.code()}")
        }
        val unsplashResponse = response.body()
        val searchResults = unsplashResponse?.searchResults ?: throw RuntimeException("Body is empty")
        //val dishes = searchResults.flatMap { it.results }
        val firstDishesList = searchResults.firstOrNull()?.results // Получаем первый список блюд
        val dishes = firstDishesList ?: listOf()
        dao.insert(dishes.map(DishEntity::fromDto))
    }
}