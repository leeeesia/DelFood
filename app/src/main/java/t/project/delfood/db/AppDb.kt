package t.project.delfood.db

import androidx.room.Database
import androidx.room.RoomDatabase
import t.project.delfood.dao.DishDao
import t.project.delfood.entity.DishEntity

@Database(
    entities = [DishEntity::class], version = 1
)

abstract class AppDb : RoomDatabase() {
    abstract fun dishDao(): DishDao
}