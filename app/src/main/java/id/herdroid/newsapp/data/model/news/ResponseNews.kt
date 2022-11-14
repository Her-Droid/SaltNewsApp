package id.herdroid.newsapp.data.model.news

import com.google.gson.annotations.SerializedName
import id.herdroid.newsapp.data.model.news.DataNews

data class ResponseNews(
        @SerializedName("status")
        val status: String = "",

        @SerializedName("totalResults")
        val totalResults: Int = 0,

        @SerializedName("articles")
        val data: List<DataNews> = emptyList()
)
