package com.example.sportssocial.data.repo

import android.content.Context
import com.example.sportssocial.data.api.RetrofitClient
import com.example.sportssocial.data.api.pojo.Article
import com.example.sportssocial.data.api.pojo.TopHeadlinesPojo
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.dao.NewsArticleDao
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.model.db.entities.NewsArticle
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import java.util.*

class NewsArticleRepositoryTest {

    lateinit var repo: NewsArticleRepository

    @Mock
    lateinit var dao: NewsArticleDao

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var inter: RetrofitClient

    @Mock
    lateinit var pojo: TopHeadlinesPojo



    @Before()
    fun setup() {
        repo = NewsArticleRepository(context)
    }
        @After
        fun tearDown() {
        }

    @Test
        fun getAllarticlesTest() {
            var fakeList: List<NewsArticle> = (listOf<NewsArticle>(
                NewsArticle(
                    123,
                    "New York Times",
                    "John Smith",
                    "Warriors Win",
                    "Game recap",
                    "articleurl",
                    "imageurl",
                    "NYT",
                    "fullarticle")
            ))

            Mockito.`when`(inter.getNews("USA", "baseball", "", 15, 10))
                .thenReturn(Observable.fromArray())

            var result = inter.getNews("", "", "", 10, 4)

            result.subscribeBy(
                onNext = {
                    Assert.assertEquals(fakeList, it)
                },
                onError = { println("error :$it") }
            )
        }

        @Test
        fun getRetrofitClient() {
        }

        @Test
        fun getArticlesbyId() {
        }

        @Test
        fun clearArticleCache() {
        }
    }
