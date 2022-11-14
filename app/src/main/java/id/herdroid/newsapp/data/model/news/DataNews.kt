package id.herdroid.newsapp.data.model.news

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import id.herdroid.newsapp.data.model.news.Source
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataNews(
    @SerializedName("source")
    var modelSource: Source?,

    @SerializedName("author")
    var author: String? = "",

    @SerializedName("title")
    var title: String? = "",

    @SerializedName("description")
    var description: String? = "",

    @SerializedName("url")
    var url: String? = "",

    @SerializedName("urlToImage")
    var urlToImage: String? = "",

    @SerializedName("publishedAt")
    var publishedAt: String? = "",

    @SerializedName("content")
    var content: String? = ""

) : Parcelable
