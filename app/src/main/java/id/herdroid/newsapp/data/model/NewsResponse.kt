package id.herdroid.newsapp.data.model


data class NewsResponse (
 var status : String? = null,
 var totalResults : Int? = null,
 var articles : ArrayList<Article> = arrayListOf()
)