package id.herdroid.newsapp.dependencyinjection.module

import android.app.Application
import android.content.Context
import id.herdroid.newsapp.constants.ApiConstants
import id.herdroid.newsapp.data.local.ArticleDao
import id.herdroid.newsapp.data.local.ArticleDatabase
import id.herdroid.newsapp.data.repository.NewsRepository
import id.herdroid.newsapp.data.repository.RequestInterceptor
import id.herdroid.newsapp.data.repository.RetrofitService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
internal class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Reusable
    internal fun provideGson(): Gson = Gson()

    @Provides
    @Reusable
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS)
        writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        addInterceptor(RequestInterceptor())
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }.build()


    @Provides
    @Reusable
    internal fun provideRetrofitService(okHttpClient: OkHttpClient): RetrofitService =
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RetrofitService::class.java)


    @Provides
    internal fun provideArticleDao(application: Application): ArticleDao =
        ArticleDatabase.getDatabase(application).articleDao()

    @Provides
    internal fun provideRepository(retrofitService: RetrofitService, articleDao: ArticleDao): NewsRepository =
        NewsRepository(retrofitService, articleDao)

}