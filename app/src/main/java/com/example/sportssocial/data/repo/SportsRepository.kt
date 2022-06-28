package com.example.sportssocial.data.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Query
import com.example.sportssocial.data.api.RetrofitClient
import com.example.sportssocial.data.api.TopHeadlinesPojo
import com.example.sportssocial.data.model.db.AppDatabase
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.model.dao.NewsArticleDao
import com.example.sportssocial.data.model.db.entities.NewsArticle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    // Gets articles from API and returns MutableLiveData<TopHeadlinesPojo>
    var newsArticleDao: NewsArticleDao? = AppDatabase.getInstance(context)?.articleDao()
    var retrofitClient = RetrofitClient.create()
    var newsArticleMutableLiveData = MutableLiveData<TopHeadlinesPojo>()

    fun getNews(countryCode: String, category: String, query: String, pageSize: Int, pageNumber: Int
    ) : MutableLiveData<TopHeadlinesPojo> {
        CoroutineScope(Dispatchers.IO).launch {

            var response = retrofitClient.getNews(countryCode, category, query, pageSize, pageNumber)

            if(response.isSuccessful) {
                newsArticleMutableLiveData.postValue(response.body())
                Log.d("Retrofit Response", "Successful")
            } else {
                Log.d("Retrofit Response", "unsuccessful: RecipeRepository: Line 30")
            }
        }
        return newsArticleMutableLiveData
    }

    // Gets recipes from Room DB and returns LiveData<List<Recipe>>

    fun upsertArticle(article: NewsArticle) {
        newsArticleDao?.upsertArticle(article)
    }

    fun getAllArticles(): LiveData<List<NewsArticle>>? {

        return newsArticleDao?.getAllArticles()
    }

    fun deleteArticle(article: NewsArticle) {
        newsArticleDao?.deleteArticle(article)
    }

    fun clearArticleCache() {
        newsArticleDao?.deleteAll()
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


