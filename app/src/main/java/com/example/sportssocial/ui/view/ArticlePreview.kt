package com.example.sportssocial.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.ui.adapters.ArticlePreviewAdapter
import com.example.sportssocial.ui.viewmodel.ArticleViewModel

class ArticlePreview : AppCompatActivity() {

    var articleList = ArrayList<NewsArticle>()
    lateinit var articlePreviewAdapter: ArticlePreviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_preview)


        this.articleList.clear()
        var viewModel = ArticleViewModel(application)
        var listView: ListView = findViewById(R.id.list_view_article)

        viewModel.getAllArticles()
        viewModel.allArticles?.observe(this) { articleList ->
            getArticles(articleList)
            articlePreviewAdapter.setItems(articleList)
        }

        articlePreviewAdapter = ArticlePreviewAdapter(this, articleList)
        listView.adapter = articlePreviewAdapter

        var readMore: TextView? = findViewById(R.id.read_more)

        readMore?.setOnClickListener {

            val newIntent = Intent(this, NewsArticleWebView::class.java)
           // newIntent.putExtra("articleId", articleList.first().id)

            startActivity(newIntent)
        }
    }

    private fun getArticles(articleList: List<NewsArticle>) {
        this.articleList.clear()
        this.articleList.addAll(articleList)
        articlePreviewAdapter.notifyDataSetChanged()
    }
}