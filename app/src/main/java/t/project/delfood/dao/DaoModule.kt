package t.project.delfood.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import t.project.delfood.db.AppDb

@InstallIn(SingletonComponent::class)
@Module
object DaoModule {

    @Provides
    fun provideDashDao(
        appDb: AppDb
    ): DishDao = appDb.dishDao()


}