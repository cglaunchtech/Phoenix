package com.example.sportssocial.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sportssocial.data.model.db.entities.NewsArticle
import io.reactivex.rxjava3.core.Observable


@Dao
interface NewsArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertArticle(article: NewsArticle)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Observable<List<NewsArticle>>

    @Query("SELECT * FROM articles where id = :id")
    fun getArticlesbyId (id: Int): Observable<NewsArticle>?

    @Delete
    fun deleteArticle(article: NewsArticle)

    @Query("delete from articles")
    fun deleteAll()

}
