package id.herdroid.newsapp.dependencyinjection.component

import android.app.Application
import id.herdroid.newsapp.NewsApplication
import id.herdroid.newsapp.dependencyinjection.builder.ActivityBuilderModule
import id.herdroid.newsapp.dependencyinjection.builder.FragmentBuilderModule
import id.herdroid.newsapp.dependencyinjection.module.AppModule
import id.herdroid.newsapp.dependencyinjection.module.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class,
    FragmentBuilderModule::class,
    ViewModelModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: NewsApplication)
}