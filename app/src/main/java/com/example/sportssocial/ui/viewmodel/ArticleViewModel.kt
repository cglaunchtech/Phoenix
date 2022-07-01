package com.example.sportssocial.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.sportssocial.data.api.TopHeadlinesPojo
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.data.repo.AthleteRepository
import com.example.sportssocial.data.repo.NewsArticleRepository
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.launch

class ArticleViewModel(app: Application) : AndroidViewModel(app) {

    private val repo: NewsArticleRepository
    val allArticles: Observable<List<NewsArticle>>?

    init {
        repo = NewsArticleRepository(app)
        allArticles = repo.getAllArticles()
    }

    fun getAllArticles() = viewModelScope.launch {
        repo.getAllArticles()
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
    fun getNews() = viewModelScope.launch {
        repo.getNews()
    }

    fun getArticlebyId(id: Int?) = id?.let { repo.getArticlesbyId(it) }

}


