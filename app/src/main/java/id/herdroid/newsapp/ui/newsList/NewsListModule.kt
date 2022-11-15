package id.herdroid.newsapp.ui.newsList

import dagger.Module
import dagger.Provides
import id.herdroid.newsapp.ui.newsList.NewsAdapter

@Module
class NewsListModule {

    @Provides
    internal fun provideNewsAdapter(): NewsAdapter = NewsAdapter()

}