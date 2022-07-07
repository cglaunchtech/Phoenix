package com.example.sportssocial.data.repo

import android.content.Context
import androidx.annotation.WorkerThread
import com.example.sportssocial.data.api.RetrofitClient
import com.example.sportssocial.data.api.RetrofitService
import com.example.sportssocial.data.api.pojo.TopHeadlinesPojo
import com.example.sportssocial.data.model.dao.NewsArticleDao
import com.example.sportssocial.data.model.db.AppDatabase
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.skydoves.sandwich.ResponseDataSource
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.suspendOnSuccess
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class NewsArticleRepository
    @Inject constructor(
        private val context : Context,
        private val retrofitClient : RetrofitClient
    ) {

    var newsArticleDao: NewsArticleDao? = AppDatabase.getInstance(context)?.articleDao()

    @WorkerThread
    suspend fun getNews() {
        clearArticleCache()
        val apiResponse = retrofitClient.getNews("us",
            "sports",
            "",
            20,
            1)

        apiResponse.suspendOnSuccess {
            if (!this.data.articles.isNullOrEmpty()) {
                for (article in this.data.articles!!) {

                    val newsArticle = NewsArticle(
                        null,
                        article.source?.name,
                        article.author,
                        article.title,
                        article.description,
                        article.url,
                        article.urlToImage.toString(),
                        article.publishedAt.toString(),
                        article.content.toString()
                    )
                    upsertArticle(newsArticle)
                }
            }
        }
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
