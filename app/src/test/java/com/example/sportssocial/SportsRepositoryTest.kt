package com.example.sportssocial

import com.example.sportssocial.data.api.Article
import com.example.sportssocial.data.api.RetrofitClient
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.dao.NewsArticleDao
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.repo.SportsRepository
import io.reactivex.rxjava3.core.Single
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
class SportsRepositoryTest {

    lateinit var repo: SportsRepository

    @Mock
    lateinit var athleteDao: AthleteDao

    @Mock
    lateinit var newsArticleDao: NewsArticleDao

    @Mock
    lateinit var inter: RetrofitClient

    @Before()
    fun setup(){
        MockitoAnnotations.openMocks(this)
//        repo = SportsRepository(inter, athleteDao, newsArticleDao)
//    }
//
//    @Test
//    fun getAllArticlesFromApiTest() {
//
//        var fakeList :List<Athlete> = (listOf<Athlete>(
//            Athlete(234,"fromtest","","","","","")
//        ))
//
//        // mock the function call to the api
//        Mockito.`when`(inter.getAllArticles())
//            .thenReturn(Single.just(fakeList))
//
//        var result = repo.getAllArticles()
//
//        result.subscribeBy(
//            onNext = {
//                Assert.assertEquals(fakeList, it)
//            },
//            onError = { println("error :$it")}
//        )
//    }
//
//    //Response object - normal list
//    @Test
//    fun getAllAthletesTest(){
//        var fakeList :List<Athlete> = (listOf<Athlete>(
//            Athlete("234","fromtest",
//                "","","","",
//                20,"","")
//        ))
//        // mock the function call to the api
//        Mockito.`when`(inter.())
//            .thenReturn(Response.success(fakeList))
//
//        var response = repo.getAllAthletes()
//
//        Assert.assertEquals(fakeList, response.body())
//
//    }
//
//    //coroutines with response object
//    @Test
//    fun getAllUsersTest(){
//        runBlocking {
//            var fakeList :List<Article> = (listOf<Article>(
//                Article(213,"fromtest","","")
//            ))
//            // mock the function call to the api
//            Mockito.`when`(inter.getAllArticles())
//                .thenReturn(Response.success(fakeList))
//
//            var response = repo.getAllArticles()
//
//            Assert.assertEquals(fakeList, response.body())
//        }
    }
}