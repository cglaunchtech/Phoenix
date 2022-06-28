package com.example.sportssocial.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sportssocial.data.api.TopHeadlinesPojo
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.data.repo.SportsRepository
import kotlinx.coroutines.launch

class ArticleViewModel(app: Application) : AndroidViewModel(app) {

    private val repo: SportsRepository
    val allArticles: LiveData<List<NewsArticle>>?
    var newsArticleMutableLiveData = MutableLiveData<TopHeadlinesPojo>()

    init {
        repo = SportsRepository(app)
        allArticles = repo.getAllArticles()
    }

    fun getAllArticles() = viewModelScope.launch {

        repo.getAllArticles()
    }

    fun getArticlebyId(id:Int) = viewModelScope.launch {

        repo.getArticlesbyId(id)
    }

    fun upsertArticle(article: NewsArticle) = viewModelScope.launch {
        repo.upsertArticle(article)
    }

    fun deleteArticle(article: NewsArticle) = viewModelScope.launch {
        repo.deleteArticle(article)
    }

    fun clearArticleCache() {
        repo.clearArticleCache()
    }

    // Get articles form API
    fun getNews(pageSize: Int, pageNumber: Int) = viewModelScope.launch {
        newsArticleMutableLiveData = repo.getNews(
            "us", "sports", "", pageSize, pageNumber
        )
    }

//    fun findArticleWithId(articleId: Long): List<NewsArticle>? {
//        return repo.findArticleWithId(articleId)
//    }
//
//        fun findArticlebyTitle(search: String): List<NewsArticle> {
//
//            return repo.findArticlesbyTitle(search)
//        }
//
//        fun findArticlebyKeyword(search: String): List<NewsArticle> {
//
//            return repo.findArticlesbyKeyword(search)
//        }
//    }
}
