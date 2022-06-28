package com.example.sportssocial.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import com.example.sportssocial.R
import com.example.sportssocial.ui.viewmodel.ArticleViewModel
import com.example.sportssocial.ui.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

lateinit var vm: ArticleViewModel
lateinit var videoView: WebView
lateinit var backBtn: Button


class NewsArticleWebView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_article_web_view)

        videoView = findViewById(R.id.videoView)
        backBtn = findViewById(R.id.backBtn)


        vm.getArticlebyId(id)?.observe(this,{ articleList ->
            getArticles(articleList)
        })

        backBtn.setOnClickListener {

        val myIntent = Intent(this, ArticlePreview::class.java)
        startActivity(myIntent)

        }
    }
}