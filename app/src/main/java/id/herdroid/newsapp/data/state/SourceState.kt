package id.herdroid.newsapp.data.state

import id.herdroid.newsapp.data.model.source.ResponseSource


sealed class SourceState {
    object Loading : SourceState()
    data class Result(val data : ResponseSource) : SourceState()
    data class Error(val error : Throwable) : SourceState()
}
