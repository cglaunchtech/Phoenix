package com.example.sportssocial

import android.content.Context
import com.example.sportssocial.data.api.RetrofitClient
import com.example.sportssocial.data.api.TopHeadlinesPojo
import com.example.sportssocial.data.model.dao.NewsArticleDao
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.data.repo.NewsArticleRepository
import com.example.sportssocial.ui.viewmodel.MainViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response


@RunWith(JUnit4::class)
class NewsArticleRepositoryUnitTest {

    lateinit var repo: NewsArticleRepository


    @Mock
    lateinit var dao: NewsArticleDao

    @Mock
    lateinit var inter: RetrofitClient

    //var context= Context


    @Before()
    fun setup(){
        repo = NewsArticleRepository(context = Context)

//        MockitoAnnotations.initMocks(this)
            MockitoAnnotations.openMocks(this)
//        setupObservers()
        }


        @Test
        fun getAllRecipeFromApiTest() {

            var fakeList :List<NewsArticle> = (listOf<NewsArticle>(
                NewsArticle( 123,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",))
            ))

            // mock the function call to the api
            Mockito.`when`(inter.getNews())
                .thenReturn(
                    Observable.concat(<TopHeadlinesPojo>!)

            var result = repo.getAllArticles()

            if (result != null) {
                result.subscribeBy(
                    onNext = {
                        Assert.assertEquals(fakeList, it)
                    },
                    onError = { println("error :$it")}
                )
            }
        }

        @Test
        fun getAllarticlesTest(){
            var fakeList :List<NewsArticle> = (listOf<NewsArticle>(
                NewsArticle( 123,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",))
            ))
            // mock the function call to the api
            Mockito.`when`(inter.getNews())
                .thenReturn(Observable<TopHeadlinesPojo>)

            var response = repo.getAllArticles()

            Assert.assertEquals(fakeList, response.body())

        }

        //coroutines with response object
        @Test
        fun getAllUsersTest(){
            runBlocking {
                var fakeList :List<NewsArticle> = (listOf<NewsArticle>(
                    NewsArticle( 123,
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",))
                ))
                // mock the function call to the api
                Mockito.`when`(inter.getNews())
                    .thenReturn(Response.success(fakeList))

                var response = repo.getAllArticles()

                Assert.assertEquals(fakeList, response.body())
            }
        }


