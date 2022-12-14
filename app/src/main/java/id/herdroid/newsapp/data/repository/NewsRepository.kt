package id.herdroid.newsapp.data.repository

import id.herdroid.newsapp.data.local.ArticleDao
import id.herdroid.newsapp.data.model.Article
import id.herdroid.newsapp.data.model.NewsResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(
    var retrofitService: RetrofitService,
    var articleDao: ArticleDao
) {

    suspend fun getAllTopHeadLines(): NewsResponse =
        retrofitService.getAllTopHeadLines()

    fun bookmarkArticle(article: Article) {
        articleDao.insertArticle(article)
    }

    fun deleteBookmarkedArticle(article: Article) {
        articleDao.deleteArticle(article)
    }

    fun getAllBookmarkedArticles(): List<Article> {
        return articleDao.getArticles()
    }

    fun removeBookmarkedArticle(article: Article) {
        getAllBookmarkedArticles().forEach { articleValue ->
            if(article.title == articleValue.title) {
                deleteBookmarkedArticle(articleValue)
            }
        }
    }

}