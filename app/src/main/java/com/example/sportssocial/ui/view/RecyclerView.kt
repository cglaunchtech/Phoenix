package com.example.sportssocial.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.ui.adapters.ArticleThumbnailAdapter
import com.example.sportssocial.ui.viewmodel.ArticleViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

class RecyclerView : AppCompatActivity() {

    var articleList = ArrayList<NewsArticle>()
    lateinit var articleThumbnailAdapter: ArticleThumbnailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        this.articleList.clear()
        var viewModel = ArticleViewModel(application)
        var recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        viewModel.getAllArticles()
        viewModel.allArticles?.observe(this) { articleList ->
            getArticles(articleList)
            articleThumbnailAdapter.setItems(articleList)
        }

        articleThumbnailAdapter =
            ArticleThumbnailAdapter(this, articleList, { position -> onCardClick(position) })
        recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerView.adapter = articleThumbnailAdapter
    }

    private fun onCardClick(position: Int) {
        val myIntent = Intent(this, ArticlePreview::class.java)
    }

    private fun getArticles(articleList: List<NewsArticle>) {
        this.articleList.clear()
        this.articleList.addAll(articleList)
        articleThumbnailAdapter.notifyDataSetChanged()
    }
}