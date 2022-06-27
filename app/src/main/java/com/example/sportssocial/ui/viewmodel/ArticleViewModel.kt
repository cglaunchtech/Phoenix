package com.example.sportssocial.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.data.repo.SportsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArticleViewModel(app: Application) : AndroidViewModel(app) {

    private val repo: SportsRepository
    val allArticles: LiveData<List<NewsArticle>>?

    init {
        repo = SportsRepository(app)
        allArticles = repo.getAllArticles()
    }

    fun getAllArticles() = viewModelScope.launch {

        repo.getAllArticles()
    }

    fun insertData(model: NewsArticle) {
        GlobalScope.launch {
            //  getDatabase(MainApplication.context).NewsArticleDao().insertItems(model)
        }
    }

    fun deleteArticle(articles: NewsArticle) = viewModelScope.launch {
        repo.deleteArticle(articles)
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
