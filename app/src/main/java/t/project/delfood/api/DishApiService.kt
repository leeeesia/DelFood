package t.project.delfood.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import t.project.delfood.api.ApiModule.Companion.ACCESS_KEY
import t.project.delfood.dto.Dish
import t.project.delfood.dto.DishResponse

interface DishApiService {

    @GET("food/search")
    fun getDishByCategory(
        @Query("apiKey") apiKey: String = ACCESS_KEY,
        @Query("query") query: String = "pizza"
    ): Call<DishResponse>
}
