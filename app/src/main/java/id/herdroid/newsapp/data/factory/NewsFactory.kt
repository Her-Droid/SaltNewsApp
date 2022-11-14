package id.herdroid.newsapp.data.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import id.herdroid.newsapp.data.model.news.DataNews
import id.herdroid.newsapp.data.source.NewsSource
import id.herdroid.newsapp.data.state.NewsState
import javax.inject.Inject

class NewsFactory @Inject constructor(
    private val source: NewsSource
) : DataSource.Factory<Int, DataNews>() {

    lateinit var liveData : MutableLiveData<NewsState>
    var sources : String = ""

    override fun create(): DataSource<Int, DataNews> {
        return source.also {
            it.sources = sources
            it.liveData = liveData
        }
    }
}