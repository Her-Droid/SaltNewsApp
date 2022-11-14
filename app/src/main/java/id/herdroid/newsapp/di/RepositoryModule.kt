package id.herdroid.newsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import id.herdroid.newsapp.data.factory.Factory
import id.herdroid.newsapp.data.network.ApiService
import id.herdroid.newsapp.data.repository.DataRepository
import id.herdroid.newsapp.data.repository.remote.RemoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRemoteRepository(
        disposable: CompositeDisposable,
        apiService: ApiService,
        factory: Factory
    ) : RemoteRepository = RemoteRepository(disposable, apiService, factory)

    @Provides
    @Singleton
    fun provideDataRepository(
        remoteRepository: RemoteRepository
    ) : DataRepository = DataRepository(remoteRepository)
}