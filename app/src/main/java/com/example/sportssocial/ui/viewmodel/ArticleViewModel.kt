package com.example.sportssocial.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.sportssocial.data.api.TopHeadlinesPojo
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.data.repo.NewsArticleRepository
import kotlinx.coroutines.launch

class ArticleViewModel(app: Application) : AndroidViewModel(app) {

    private val repo: NewsArticleRepository
    val allArticles: LiveData<List<NewsArticle>>?
    var newsArticleMutableLiveData = MutableLiveData<TopHeadlinesPojo>()
    var currArticle = MutableLiveData<NewsArticle>()

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
    fun getNews(pageSize: Int, pageNumber: Int) = viewModelScope.launch {
        newsArticleMutableLiveData = repo.getNews(
            "us", "sports", "", pageSize, pageNumber
        )
    }

    fun getArticlebyId(id: Int?) = viewModelScope.launch {
        currArticle.value?.let { current ->

            current.id = repo.getArticlesbyId(id!!)?.value?.id

            val article: LiveData<NewsArticle> = Transformations.switchMap(currArticle) { article ->
                repo.getArticlesbyId(article.id!!)
            }
        }
    }



}


