package id.herdroid.newsapp.dependencyinjection.builder

import id.herdroid.newsapp.ui.bookmarked.BookmarkedFragment
import id.herdroid.newsapp.ui.newsDetails.NewsDetailsFragment
import id.herdroid.newsapp.ui.newsList.NewsFragment
import id.herdroid.newsapp.ui.newsList.NewsListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
 * This builder provides android injector service to fragments
 */
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [NewsListModule::class])
    abstract fun bindNewsFragment(): NewsFragment

    @ContributesAndroidInjector
    abstract fun bindNewsDetailsFragment(): NewsDetailsFragment

    @ContributesAndroidInjector(modules = [NewsListModule::class])
    abstract fun bindBookmarkedFragment(): BookmarkedFragment

}