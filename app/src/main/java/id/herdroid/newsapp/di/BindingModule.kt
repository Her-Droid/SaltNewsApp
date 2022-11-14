package id.herdroid.newsapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.herdroid.newsapp.data.repository.DataRepository
import id.herdroid.newsapp.data.repository.Repository

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {
    @Binds
    abstract fun bindingRepository(
        dataRepository: DataRepository
    ) : Repository
}