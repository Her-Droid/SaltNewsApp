package id.herdroid.newsapp.data.repository

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import id.herdroid.newsapp.data.model.categories.Categories
import id.herdroid.newsapp.data.model.news.DataNews
import id.herdroid.newsapp.data.state.NewsState
import id.herdroid.newsapp.data.state.SourceState

interface Repository {

    fun getHeadlines(
        callback: MutableLiveData<NewsState>
    )

    fun getSources(
        categories: String,
        callback : MutableLiveData<SourceState>
    )

    fun getNews(
        sources : String,
        callback: MutableLiveData<NewsState>,
        data: MutableLiveData<PagedList<DataNews>>,
        lifecycleOwner: LifecycleOwner
    )

    fun searchNews(
        query : String,
        callback: MutableLiveData<NewsState>,
        data: MutableLiveData<PagedList<DataNews>>,
        lifecycleOwner: LifecycleOwner
    )

    fun getCategories(context : Context) : List<Categories>

    fun getDisposable() : CompositeDisposable
}