package com.example.sportssocial.ui.view

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.data.repo.FirestoneRepo
import com.example.sportssocial.ui.viewmodel.ArticleViewModel
import com.example.sportssocial.util.Constants.Companion.FIRESTORE
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var firestore = FirestoneRepo()
        var viewModel = ArticleViewModel(application)
        viewModel.getNews(20, 1)
        viewModel.clearArticleCache()
        viewModel.newsArticleMutableLiveData.observe(this) { it ->
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
                viewModel.upsertArticle(article)
            }


            btn_test_news_preview.setOnClickListener {
                val intent = Intent(this, ArticlePreview::class.java)
                startActivity(intent)

            }

            btn_test_recycler_view.setOnClickListener {
                val intent = Intent(this, RecyclerView::class.java)
                startActivity(intent)
            }

            btnToRegister.setOnClickListener{
                val intent = Intent(this, SignUp::class.java)
                startActivity(intent)
            }

            btnToPull.setOnClickListener{
                firestore.userList.observe(this){
                    testUser.text = it.toString()
                }

            }
        }
    }

}