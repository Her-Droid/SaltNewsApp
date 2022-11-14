package id.herdroid.newsapp.data.factory

import javax.inject.Inject

data class Factory @Inject constructor(
    val newsFactory : NewsFactory,
    val searchFactory : SearchFactory
)
