package id.herdroid.newsapp.data.repository

import id.herdroid.newsapp.data.model.NewsResponse
import retrofit2.http.GET

interface RetrofitService {

    @GET("top-headlines")
    suspend fun getAllTopHeadLines() : NewsResponse
}