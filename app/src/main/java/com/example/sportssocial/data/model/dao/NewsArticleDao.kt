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

    @Delete
    fun deleteArticle(article: NewsArticle)

    @Query("delete from articles")
    fun deleteAll()
//
//    @Query("SELECT * FROM articles where id like :articleId")
//    fun findArticleWithId(articleId: Long): List<NewsArticle>

//    @Query("select * from articles where title like :search")
//    fun findArticlesbyTitle(search: String): List<NewsArticle>
//
//
//    @Query("select * from articles where content like :search")
//    fun findArticlesbykeywords(search: String): List<NewsArticle>
//
//
//    @Query("select * from articles where upper(content) like '%' || upper(:searchText) || '%' ")
//    fun search(searchText : String): LiveData<List<NewsArticle>>
}
