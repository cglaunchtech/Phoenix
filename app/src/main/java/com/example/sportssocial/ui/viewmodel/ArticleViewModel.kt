package com.example.sportssocial.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.data.repo.NewsArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repo : NewsArticleRepository,
    private val app : Application
) : AndroidViewModel(app) {

    val allArticles: Observable<List<NewsArticle>>?

    init {
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


