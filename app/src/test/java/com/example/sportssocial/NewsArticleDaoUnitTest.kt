package com.example.sportssocial

import android.app.Application
import com.example.sportssocial.data.api.RetrofitClient
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.dao.NewsArticleDao
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.data.repo.AthleteRepository
import com.example.sportssocial.data.repo.NewsArticleRepository
import com.example.sportssocial.ui.viewmodel.ArticleViewModel
import com.example.sportssocial.ui.viewmodel.MainViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)

class NewsArticleDaoUnitTest {

    lateinit var dao: NewsArticleDao

    @Mock
    lateinit var repo: NewsArticleRepository

    @Mock
    lateinit var articleList: Observer<List<NewsArticle>>

    @Mock
    lateinit var vm: ArticleViewModel

    @Mock
    lateinit var inter: RetrofitClient

    @Mock
    lateinit var app: Application

}


//    @Before
//    fun setUp(){
//        MockitoAnnotations.openMocks(this)
//
//        vm = ArticleViewModel(app)
////        setupObservers()
//    }
//    private fun setupObservers(){
//        articleList = Mockito.mock(Observer::class.java) as Observer<List<NewsArticle>>
//    }
//
//    @Test
//    fun getAllApiNewsArticles(){
//        var fakeList :List<NewsArticle> = (listOf<NewsArticle>(
//            NewsArticle(123,
//                "New York Times",
//                "John Smith",
//                "Warriors Win",
//                "Game recap",
//                "articleurl",
//                "imageurl",
//                "NYT",
//                "fullarticle")
 //       ))

//        Mockito.`when`(repo.getAllArticles())
//            .thenReturn(Observable.fromArray(fakeList))


//        val result = vm.getAllArticles()

//        result.subscribeBy(
//            onNext = {
//                assertEquals(listOf<NewsArticle>(
//                    NewsArticle(123,
//                        "New York Times",
//                        "John Smith",
//                        "Warriors Win",
//                        "Game recap",
//                        "articleurl",
//                        "imageurl",
//                        "NYT",
//                        "fullarticle")
//                )) it)
//            },
//            onError = { println("error :$it")}
//        )
//    }
//
//    @Test
//    fun `given NewsArticleRepository when calling newsArticlelist, list is empty and assert it is empty`() {
//
//        var fakeList :List<NewsArticle> = (listOf<NewsArticle>(
//            NewsArticle(null,
//                " ",
//                " ",
//                " ",
//                " ",
//                " ",
//                " ",
//                " ",
//                " ")
//        ))
//
//        Mockito.`when`(repo.getAllArticles())
//            .thenReturn(Observable.fromArray(fakeList))
//
//
//        val result = vm.getAllArticles()
//
//        result.subscribeBy(
//            onNext = {
//                assertEquals(listOf<NewsArticle>(
//                    NewsArticle(123,
//                        " ",
//                        " ",
//                        " ",
//                        " ",
//                        "",
//                        " ",
//                        " ",
//                        " ")
//                )) it)
//            },
//            onError = { println("error :$it")}
//        )
//    }
//}