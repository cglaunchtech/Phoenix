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


        btn_test_news_preview.setOnClickListener {
            val intent = Intent(this, ArticlePreview::class.java)
            startActivity(intent)
        }

        btn_test_recycler_view.setOnClickListener {
            val intent = Intent(this, RecyclerView::class.java)
            startActivity(intent)
        }

        btnToRegister.setOnClickListener {
            AUTH.signOut()
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnToPull.setOnClickListener {
            firestore.getAllProfiles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        testUser.text = it.toString()
                    },
                    onComplete = {

                    },
                    onError = { e -> Timber.e(e)}
                )
        }
    }
}
