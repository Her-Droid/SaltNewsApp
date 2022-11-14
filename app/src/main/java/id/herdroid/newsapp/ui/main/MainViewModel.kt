package id.herdroid.newsapp.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.herdroid.newsapp.data.model.categories.Categories
import id.herdroid.newsapp.data.repository.Repository
import id.herdroid.newsapp.data.state.NewsState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val state : MutableLiveData<NewsState> by lazy {
        MutableLiveData()
    }
    fun getCategories(context: Context) : List<Categories> {
        return repository.getCategories(context)
    }

    fun getHeadlines(){
        repository.getHeadlines(state)
    }
}