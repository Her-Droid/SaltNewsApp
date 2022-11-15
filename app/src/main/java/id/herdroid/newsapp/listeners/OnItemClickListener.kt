package id.herdroid.newsapp.listeners

import id.herdroid.newsapp.data.model.Article


interface OnItemClickListener {
    fun onItemClick(item: Article?)
}