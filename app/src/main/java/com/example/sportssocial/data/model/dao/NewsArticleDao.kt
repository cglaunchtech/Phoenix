package com.example.sportssocial.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sportssocial.data.model.db.entities.NewsArticle


@Dao
interface NewsArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertArticle(article: NewsArticle)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<NewsArticle>>

    @Query("SELECT * FROM articles where id = :id")
    fun getArticlesbyId (id: Int): LiveData<NewsArticle>?

    @Delete
    fun deleteArticle(article: NewsArticle)

    @Query("delete from articles")
    fun deleteAll()

}
