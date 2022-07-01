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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class NewsArticleRepository (context: Context){


    // News Articles
    // Gets articles from API and returns MutableLiveData<TopHeadlinesPojo>
    var newsArticleDao: NewsArticleDao? = AppDatabase.getInstance(context)?.articleDao()
    var retrofitClient = RetrofitClient.create()

    fun getNews() {
        clearArticleCache()
        retrofitClient.getNews("us", "sports", "", 20, 1)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeBy(
                onNext = {
                    for (index in 0..(it.articles?.lastIndex!!)) {

                        var article = NewsArticle(
                            null,
                            it.articles[index].source?.name,
                            it.articles[index].author,
                            it.articles[index].title,
                            it.articles[index].description,
                            it.articles[index].url,
                            it.articles[index].urlToImage.toString(),
                            it.articles[index].publishedAt.toString(),
                            it.articles[index].content.toString()
                        )
                        upsertArticle(article)
                    }
                },
                onError = { e -> Timber.e(e) }
            )
    }

    fun upsertArticle(article: NewsArticle) {
        newsArticleDao?.upsertArticle(article)
    }

    fun getAllArticles(): Observable<List<NewsArticle>>? {

        return newsArticleDao?.getAllArticles()
    }

    fun getArticlesbyId(articleId: Int): Observable<NewsArticle>? {
        return newsArticleDao?.getArticlesbyId(articleId)
    }

    fun deleteArticle(article: NewsArticle) {
        newsArticleDao?.deleteArticle(article)
    }

    fun clearArticleCache() {
        newsArticleDao?.deleteAll()
    }
}
