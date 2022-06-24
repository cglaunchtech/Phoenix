package com.example.sportssocial.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sportssocial.data.model.Articles


@Dao
interface NewsArticleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItems(articles: Articles)

    @Query("SELECT * FROM Articles")
    fun getAllArticles(): LiveData<List<Articles>>

    @Delete
    fun deleteArticle(articles: Articles)

    @Query("select * from articles where title like :search")
    fun findArticlesbyTitle(search: String): List<Articles>


    @Query("select * from articles where content like :search")
    fun findArticlesbykeywords(search: String): List<Articles>


    @Query("select * from articles where upper(content) like '%' || upper(:searchText) || '%' ")
    fun search(searchText : String): LiveData<List<Articles>>
}
