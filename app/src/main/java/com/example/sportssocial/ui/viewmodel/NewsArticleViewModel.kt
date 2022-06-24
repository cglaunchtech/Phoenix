package com.example.sportssocial.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.sportssocial.data.model.Articles
import com.example.sportssocial.data.repo.SportsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsArticleViewModel (app: Application): AndroidViewModel(app) {

    private val repo: SportsRepository
    val allArticles: LiveData<List<Articles>>?

    init {
        repo = SportsRepository(app)
        allArticles = repo.getAllArticles()
    }

    fun getAllArticles() = viewModelScope.launch {

        repo.getAllArticles()
    }

    fun insertData(model: Articles) {
        GlobalScope.launch {
            //  getDatabase(MainApplication.context).NewsArticleDao().insertItems(model)
        }

        fun deleteArticle(articles: Articles) = viewModelScope.launch {
            repo.deleteArticle(articles)
        }

        fun findArticlebyTitle(search: String): List<Articles> {

            return repo.findArticlesbyTitle(search)
        }

        fun findArticlebyKeyword(search: String): List<Articles> {

            return repo.findArticlesbyKeyword(search)
        }
    }
}
