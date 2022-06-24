package com.example.sportssocial.data.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sportssocial.data.db.AppDatabase
import com.example.sportssocial.data.model.Articles

import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.Athletes
import com.example.sportssocial.data.model.dao.NewsArticleDao

class SportsRepository (context: Context) {
    var db: AthleteDao? = AppDatabase.getInstance(context)?.athleteDao()

    fun getAllAthletes(): LiveData<List<Athletes>>? {

        return db?.selectAthlete()
    }

    fun insertAthlete(athletes: Athletes) {

        db?.insertAthlete(athletes)
    }

    fun updateAthlete(athletes: Athletes) {
        db?.updateAthlete(athletes)
    }

    fun deleteAthlete(athletes: Athletes) {
        db?.deleteAthlete(athletes)
    }

    fun findAthletebyUsername(search: String): List<Athletes> {

        return db?.findAthletesbyUsername(search)!!
    }

    fun searchAthletes(searchText: String): LiveData<List<Athletes>>? {
        return db?.search(searchText)
    }

    var dbNews: NewsArticleDao? = AppDatabase.getInstance(context)?.NewsArticleDao()

    fun getAllArticles(): LiveData<List<Articles>>? {

        return dbNews?.getAllArticles()
    }

    fun deleteArticle(articles: Articles) {
        dbNews?.deleteArticle(articles)
    }

    fun findArticlesbyTitle(search: String): List<Articles> {

        return dbNews?.findArticlesbyTitle(search)!!
    }

    fun findArticlesbyKeyword(search: String): List<Articles> {

        return dbNews?.findArticlesbykeywords(search)!!
    }

    fun searchArticles(searchText: String): LiveData<List<Articles>>? {
        return dbNews?.search(searchText)

    }
}


