package t.project.delfood.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import t.project.delfood.repository.DishRepository
import t.project.delfood.repository.DishRepositoryImpl
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindsPostRepository(
        impl: DishRepositoryImpl
    ): DishRepository

}