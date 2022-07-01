package com.example.sportssocial.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import com.example.sportssocial.R
import com.example.sportssocial.ui.viewmodel.ArticleViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class NewsArticleWebView : AppCompatActivity() {
    lateinit var vm: ArticleViewModel
    lateinit var webView: WebView
    lateinit var backBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_article_web_view)


        vm = ArticleViewModel(application)
        webView = findViewById(R.id.webView)
        backBtn = findViewById(R.id.backBtn)

        backBtn.setOnClickListener {

            val newIntent = Intent(this, ArticlePreview::class.java)
            startActivity(newIntent)

        }

        vm.getArticlebyId(intent.getIntExtra("articleId", 0))
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeBy(
                onNext = {
                    setWebviewUrl((it.url))
                },
                onError = {e -> Timber.e(e) }
            )

        webView.webViewClient = WebViewClient()

        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)

    }
        fun setWebviewUrl (url:String?) {
        webView.loadUrl(url!!)
    }

}


