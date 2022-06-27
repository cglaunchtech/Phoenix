package com.example.sportssocial.data.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sportssocial.data.model.db.AppDatabase
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.model.dao.NewsArticleDao
import com.example.sportssocial.data.model.db.entities.NewsArticle

class SportsRepository (context: Context) {
    var db: AthleteDao? = AppDatabase.getInstance(context)?.athleteDao()

    fun getAllAthletes(): LiveData<List<Athlete>>? {

        return db?.selectAthlete()
    }

    fun insertAthlete(athletes: Athlete) {

        db?.insertAthlete(athletes)
    }

    fun updateAthlete(athletes: Athlete) {
        db?.updateAthlete(athletes)
    }

    fun deleteAthlete(athlete: Athlete) {
        db?.deleteAthlete(athlete)
    }

    fun findAthletebyUsername(search: String): List<Athlete> {

        return db?.findAthletesbyUsername(search)!!
    }

    fun searchAthletes(searchText: String): LiveData<List<Athlete>>? {
        return db?.search(searchText)
    }

    // News Articles

    var newsArticleDao: NewsArticleDao? = AppDatabase.getInstance(context)?.articleDao()


    suspend fun upsertArticle(article: NewsArticle): Unit? {
        return newsArticleDao?.upsertArticle(article)
    }

    fun getAllArticles(): LiveData<List<NewsArticle>>? {

        return newsArticleDao?.getAllArticles()
    }

    suspend fun deleteArticle(article: NewsArticle) {
        newsArticleDao?.deleteArticle(article)
    }

//    fun findArticleWithId(articleId: Long): List<NewsArticle>? {
//        return newsArticleDao?.findArticleWithId(articleId)
//    }

//    fun findArticlesbyTitle(search: String): List<NewsArticle> {
//
//        return newsArticleDao?.findArticlesbyTitle(search)!!
//    }
//
//    fun findArticlesbyKeyword(search: String): List<NewsArticle> {
//
//        return newsArticleDao?.findArticlesbykeywords(search)!!
//    }
//
//    fun searchArticles(searchText: String): LiveData<List<NewsArticle>>? {
//        return newsArticleDao?.search(searchText)
//
//    }
}


