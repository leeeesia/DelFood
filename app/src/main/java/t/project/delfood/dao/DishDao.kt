package t.project.delfood.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import t.project.delfood.entity.DishEntity

@Dao
interface DishDao {
    @Query("SELECT * FROM DishEntity ORDER BY id DESC")
    fun getAll(): Flow<List<DishEntity>>

    @Query("SELECT COUNT(*) == 0 FROM DishEntity")
    suspend fun isEmpty():Boolean


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dish: DishEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dishes: List<DishEntity>)

}
