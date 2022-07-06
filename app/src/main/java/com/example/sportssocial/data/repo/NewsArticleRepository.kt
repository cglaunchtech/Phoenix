package com.example.sportssocial.data.repo

import android.content.Context
import androidx.annotation.WorkerThread
import com.example.sportssocial.data.api.RetrofitClient
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

class NewsArticleRepository(context: Context) {

    // request API call Asynchronously and holding successful response data.
    private val dataSource = ResponseDataSource<List<TopHeadlinesPojo>>()

    // News Articles
    // Gets articles from API and returns MutableLiveData<TopHeadlinesPojo>
    var newsArticleDao: NewsArticleDao? = AppDatabase.getInstance(context)?.articleDao()
    var retrofitClient = RetrofitClient.create()

    @WorkerThread
    suspend fun getNews() {
        clearArticleCache()
        val apiResponse = retrofitClient.getNews("us", "sports", "", 20, 1)

//        @WorkerThread
//        fun loadVideoList(id: Int) = flow {
//            val movie = movieDao.getMovie(id)
//            var videos = movie.videos
        apiResponse.suspendOnSuccess {
            if (!this.data.articles.isNullOrEmpty()) {
                for (article in this.data.articles!!) {

                    val newsArticle = NewsArticle (
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

//            if (videos.isNullOrEmpty()) {
//                movieService.fetchVideos(id)
//                    .suspendOnSuccess {
//                        videos = data.results
//                        movie.videos = videos
//                        movieDao.updateMovie(movie)
//                        emit(videos)
//                    }
//            } else {
//                emit(videos)
//            }
//        }.flowOn(Dispatchers.IO)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy(
//                onNext = {
//                    for (index in 0..(it.articles!!.lastIndex)) {
//
//                        var article = NewsArticle(
//                            null,
//                            it.articles[index].source?.name,
//                            it.articles[index].author,
//                            it.articles[index].title,
//                            it.articles[index].description,
//                            it.articles[index].url,
//                            it.articles[index].urlToImage.toString(),
//                            it.articles[index].publishedAt.toString(),
//                            it.articles[index].content.toString()
//                        )
//                        upsertArticle(article)
//                    }
//                },
//                onError = { e -> Timber.e(e) }
//            )
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
