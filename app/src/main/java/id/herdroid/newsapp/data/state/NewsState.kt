package id.herdroid.newsapp.data.state

import id.herdroid.newsapp.data.model.news.ResponseNews

sealed class NewsState {
    object Loading : NewsState()
    data class Result(val data : ResponseNews) : NewsState()
    data class Error(val error : Throwable) : NewsState()
}
