package id.herdroid.newsapp.ui.newsDetails

import androidx.lifecycle.viewModelScope
import id.herdroid.newsapp.data.model.Article
import id.herdroid.newsapp.data.repository.NewsRepository
import id.herdroid.newsapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model for [[NewsDetailsFragment]]
 */
class NewsDetailsViewModel @Inject constructor(private val repository: NewsRepository) :
    BaseViewModel() {

    fun bookmarkArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.bookmarkArticle(article)
        }
    }

    fun removeBookmarkArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeBookmarkedArticle(article)
        }
    }

}