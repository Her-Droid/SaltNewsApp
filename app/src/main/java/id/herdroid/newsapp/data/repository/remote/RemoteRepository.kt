package id.herdroid.newsapp.data.repository.remote

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import org.json.JSONArray
import id.herdroid.newsapp.data.factory.Factory
import id.herdroid.newsapp.data.model.categories.Categories
import id.herdroid.newsapp.data.model.news.DataNews
import id.herdroid.newsapp.data.network.ApiService
import id.herdroid.newsapp.data.repository.Repository
import id.herdroid.newsapp.data.state.NewsState
import id.herdroid.newsapp.data.state.SourceState
import id.herdroid.newsapp.utils.Extentions.loadJSONFromAssets
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val disposable: CompositeDisposable,
    private val apiService: ApiService,
    private val factory: Factory
) : Repository {
    override fun getHeadlines(callback: MutableLiveData<NewsState>) {
        apiService.getHeadline()
                .map<NewsState>(NewsState::Result)
                .onErrorReturn(NewsState::Error)
                .toFlowable()
                .startWith(NewsState.Loading)
                .subscribe(callback::postValue)
                .let { return@let disposable::add }
    }

    override fun getSources(categories: String, callback: MutableLiveData<SourceState>) {
        apiService.getSource(categories)
            .map<SourceState>(SourceState::Result)
            .onErrorReturn(SourceState::Error)
            .toFlowable()
            .startWith(SourceState.Loading)
            .subscribe(callback::postValue)
            .let { return@let disposable::add }
    }

    override fun getNews(
        sources: String,
        callback: MutableLiveData<NewsState>,
        data: MutableLiveData<PagedList<DataNews>>,
        lifecycleOwner: LifecycleOwner
    ) {
        LivePagedListBuilder(
            factory.newsFactory.also {
                it.liveData = callback
                it.sources = sources },
            10
        ).build().observe(lifecycleOwner, data::postValue)
    }

    override fun searchNews(
        query: String,
        callback: MutableLiveData<NewsState>,
        data: MutableLiveData<PagedList<DataNews>>,
        lifecycleOwner: LifecycleOwner
    ) {
        LivePagedListBuilder(
                factory.searchFactory.also {
                    it.liveData = callback
                    it.query = query },
                10
        ).build().observe(lifecycleOwner, data::postValue)
    }

    override fun getCategories(context : Context) : List<Categories> {
        val dataCategories = ArrayList<Categories>()
        val facilityJsonArray = JSONArray(context.loadJSONFromAssets("categories.json"))

        for (i in 0 until facilityJsonArray.length()){
            val json = facilityJsonArray.getJSONObject(i)
            val categories = Categories(
                    json.getString("id"),
                    json.getString("title"),
                    json.getString("icon"),
                    json.getString("color")
            )

            dataCategories.add(categories)
        }

        return dataCategories
    }


    override fun getDisposable(): CompositeDisposable {
        return disposable
    }
}