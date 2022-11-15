package id.herdroid.newsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import id.herdroid.newsapp.data.model.Article

@Dao
interface ArticleDao {

    @Query("Select * from article")
    fun getArticles(): List<Article>

    @Insert
    fun insertArticle(article: Article)

    @Delete
    fun deleteArticle(article: Article)

}
