package com.example.sportssocial.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.ui.adapters.ArticlePreviewAdapter
import com.example.sportssocial.ui.viewmodel.ArticleViewModel

class ArticlePreview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_preview)

        val articleID: Long = intent.getLongExtra("articleId",0)

        var viewModel =  ArticleViewModel(application)
        var listView: ListView = findViewById(R.id.list_view_article)
//        var articleList = viewModel.findArticleWithId(articleID)
//        val adapter = ArticlePreviewAdapter(this, articleList as ArrayList<NewsArticle>)

//        listView.adapter = adapter
    }
}