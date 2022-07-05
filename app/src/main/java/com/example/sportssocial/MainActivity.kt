package com.example.sportssocial.ui.view

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.data.repo.FirestoneRepo
import com.example.sportssocial.ui.navigation.*
import com.example.sportssocial.ui.viewmodel.ArticleViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_container.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val transaction = supportFragmentManager
        //instantiates firestore
/*        var firestore = FirestoneRepo()

        //starts manipulating viewmodel
        var viewModel = ArticleViewModel(application)
        viewModel.getNews(20, 1)
        viewModel.clearArticleCache()
        viewModel.newsArticleMutableLiveData.observe(this) { it ->
            for (index in 0..(it.articles?.lastIndex!!)) {
                Timber.d("MainActivity: line 24")
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
                viewModel.upsertArticle(article)
            }
        }*/

    }
}