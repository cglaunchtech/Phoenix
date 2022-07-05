package com.example.sportssocial

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



    @Before
    fun setUp(){
//        MockitoAnnotations.initMocks(this)
        MockitoAnnotations.openMocks(this)
//        repo = BookRepository(inter, dao)
        vm = ArticleViewModel(repo)
//        setupObservers()
    }
    private fun setupObservers(){
        articleList = Mockito.mock(Observer::class.java) as Observer<List<NewsArticle>>
    }

    @Test
    fun getAllApiNewsArticles(){
        var fakeList :List<NewsArticle> = (listOf<NewsArticle>(
            NewsArticle(234,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "")
        ))

        Mockito.`when`(repo.getAllArticles())
            .thenReturn(Observable.fromArray(fakeList))


        val result = vm.getAllArticles()

        result.subscribeBy(
            onNext = {
                assertEquals(listOf<NewsArticle>(
                    NewsArticle(293,
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "")
                )
                ),it)
            },
            onError = { println("error :$it")}
        )
    }

    @Test
    fun `given repository when calling athleteList then list is empty and assert its empty`() {

    }
}
}