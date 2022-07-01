package com.example.sportssocial.data.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sportssocial.data.api.RetrofitClient
import com.example.sportssocial.data.api.TopHeadlinesPojo
import com.example.sportssocial.data.model.dao.NewsArticleDao
import com.example.sportssocial.data.model.db.AppDatabase
import com.example.sportssocial.data.model.db.entities.NewsArticle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class NewsArticleRepository (context: Context){


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
                Timber.d("Retrofit Response: Successful")
            } else {
                Timber.d("Retrofit Response: Unsuccessful: NewsArticleRepository: Line 30")
            }
        }
        return newsArticleMutableLiveData
    }


    fun upsertArticle(article: NewsArticle) {
        newsArticleDao?.upsertArticle(article)
    }

    fun getAllArticles(): LiveData<List<NewsArticle>>? {
        return newsArticleDao?.getAllArticles()
    }

    fun getArticlesbyId(articleId: Int): LiveData<NewsArticle>? {
        return newsArticleDao?.getArticlesbyId(articleId)
    }

    fun deleteArticle(article: NewsArticle) {
        newsArticleDao?.deleteArticle(article)
    }

    fun clearArticleCache() {
        newsArticleDao?.deleteAll()
    }
}
