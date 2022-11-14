package id.herdroid.newsapp.data.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import id.herdroid.newsapp.data.model.news.DataNews
import id.herdroid.newsapp.data.source.SearchSource
import id.herdroid.newsapp.data.state.NewsState
import javax.inject.Inject

class SearchFactory @Inject constructor(
    private val source: SearchSource
) : DataSource.Factory<Int, DataNews>() {

    lateinit var liveData : MutableLiveData<NewsState>
    var query : String = ""

    override fun create(): DataSource<Int, DataNews> {
        return source.also {
            it.query = query
            it.liveData = liveData
        }
    }
}