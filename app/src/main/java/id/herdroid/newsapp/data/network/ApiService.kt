package id.herdroid.newsapp.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import id.herdroid.newsapp.data.model.news.ResponseNews
import id.herdroid.newsapp.data.model.source.ResponseSource

interface ApiService {

    @GET("top-headlines?country=us")
    fun getHeadline(): Single<ResponseNews>

    @GET("sources")
    fun getSource(
            @Query("category") category: String?
    ): Single<ResponseSource>

    @GET("top-headlines")
    fun getNews(
        @Query("sources") sources: String?,
        @Query("page") page: Int,
    ): Single<ResponseNews>

    @GET("everything")
    fun searchNews(
        @Query("q") keyword: String?,
        @Query("page") page: Int,
    ): Single<ResponseNews>
}