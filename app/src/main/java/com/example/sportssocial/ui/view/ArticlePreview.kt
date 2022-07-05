package com.example.sportssocial.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView

import com.example.sportssocial.R
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.ui.adapters.ArticlePreviewAdapter
import com.example.sportssocial.ui.viewmodel.ArticleViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

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

        viewModel.allArticles
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeBy(
                onNext = {
                    getArticles(it)
                },
                onComplete = {
                    articlePreviewAdapter.setItems(articleList)
                },
                onError = {e -> Timber.e(e)}
            )


        articlePreviewAdapter = ArticlePreviewAdapter(this, articleList)
        listView.adapter = articlePreviewAdapter

    }

    private fun getArticles(articleList: List<NewsArticle>) {
        this.articleList.clear()
        this.articleList.addAll(articleList)
        articlePreviewAdapter.notifyDataSetChanged()

        var readMore: Button? = findViewById(R.id.text_view_read_more)

        readMore?.setOnClickListener {

            val newIntent = Intent(this, NewsArticleWebView::class.java)
            newIntent.putExtra("articleId", articleList.first().id)

            startActivity(newIntent)
        }
    }
}