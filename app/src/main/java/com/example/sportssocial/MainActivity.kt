package com.example.sportssocial.ui.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sportssocial.R
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.ui.viewmodel.ArticleViewModel
import com.example.sportssocial.util.Constants.Companion.AUTH
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var firestore = FirestoreRepo()
        var viewModel = ArticleViewModel(application)
        viewModel.getNews()

        }
}
