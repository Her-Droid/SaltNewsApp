package id.herdroid.newsapp.dependencyinjection.builder

import id.herdroid.newsapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
 * The module which provides the android injection service to Activities.
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}