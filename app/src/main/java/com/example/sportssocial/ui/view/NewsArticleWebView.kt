package com.example.sportssocial.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.activity.viewModels
import com.example.sportssocial.R
import com.example.sportssocial.ui.viewmodel.ArticleViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class NewsArticleWebView : AppCompatActivity() {
    lateinit var webView: WebView
    lateinit var backBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_article_web_view)


        val vm : ArticleViewModel by viewModels()
        webView = findViewById(R.id.webView)
        backBtn = findViewById(R.id.backBtn)

        backBtn.setOnClickListener {

            val newIntent = Intent(this, ArticlePreview::class.java)
            startActivity(newIntent)

        }
        //   try {
        vm.getArticlebyId(intent.getIntExtra("articleId", 0))
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeBy(
                onNext = {
                    setWebviewUrl((it.url))
                },
                onError = { e -> Timber.e(e) }
            )

        webView.webViewClient = WebViewClient()

        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)

    }
//} catch (e: Exception) {
//    Timber.e(e)

    fun setWebviewUrl(url: String?) {

        try {
            webView.loadUrl(url!!)

        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}


