package com.example.sportssocial.ui.view

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.data.repo.FirestoneRepo
import com.example.sportssocial.ui.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.activity_main.*

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
                val intent = Intent(this, RegistrationForm::class.java)
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