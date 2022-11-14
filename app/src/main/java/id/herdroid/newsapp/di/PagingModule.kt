package id.herdroid.newsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import id.herdroid.newsapp.data.factory.Factory
import id.herdroid.newsapp.data.factory.NewsFactory
import id.herdroid.newsapp.data.factory.SearchFactory
import id.herdroid.newsapp.data.network.ApiService
import id.herdroid.newsapp.data.source.NewsSource
import id.herdroid.newsapp.data.source.SearchSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PagingModule {
    @Provides
    @Singleton
    fun provideFactory(
        newsFactory: NewsFactory,
        searchFactory: SearchFactory
    ) : Factory = Factory(
            newsFactory,
            searchFactory
    )

    @Provides
    @Singleton
    fun provideNewsSource(
        apiService: ApiService,
        disposable: CompositeDisposable
    ) : NewsSource = NewsSource(apiService, disposable)

    @Provides
    @Singleton
    fun provideSearchSource(
        apiService: ApiService,
        disposable: CompositeDisposable
    ) : SearchSource = SearchSource(apiService, disposable)

    @Provides
    @Singleton
    fun provideNewsFactory(
        newsSource: NewsSource
    ) : NewsFactory = NewsFactory(newsSource)

    @Provides
    @Singleton
    fun provideSearchFactory(
        searchSource: SearchSource
    ) : SearchFactory = SearchFactory(searchSource)
}