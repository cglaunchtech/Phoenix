package com.example.sportssocial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.ui.view.ArticlePreview
import com.example.sportssocial.ui.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var viewModel = ArticleViewModel(application)
        viewModel.getNews(20, 1)
        viewModel.newsArticleMutableLiveData.observe(this){
            for(index in 0..(it.articles?.lastIndex!!)){
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
        }

        btn_test.setOnClickListener{
            val intent = Intent(this, ArticlePreview::class.java)
            startActivity(intent)
        }
    }
}