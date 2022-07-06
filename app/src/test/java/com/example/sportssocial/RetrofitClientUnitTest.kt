package com.example.sportssocial

import com.example.sportssocial.data.api.RetrofitClient
import com.example.sportssocial.data.repo.NewsArticleRepository
import com.google.common.truth.ExpectFailure.assertThat
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@RunWith(JUnit4::class)
class RetrofitClientUnitTest {

    lateinit var inter: RetrofitClient
    lateinit var mockServer: MockWebServer

    @Mock
    lateinit var repo: NewsArticleRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mockServer = MockWebServer()

        inter = Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitClient::class.java)
    }

    @Test
    fun getAllnewsTest() {

        var mockRes = MockResponse()
        mockServer.enqueue(mockRes.setBody("[]"))

        val req = inter.getNews("", "", "", 0, 0)

        req.subscribeBy(
            onNext = {
                it
            },
            onError = {println("error :$it")}
        )
        val res = mockServer.takeRequest()

        Assert.assertEquals("v2/top-headlines", res.path)
    }
        @After
        fun destroy() {
            mockServer.shutdown()
        }







//https://github.com/muryno/GlobalNewsMvvmKotlinCleanArchitecture/blob/master/app/src/test/java/com/ubn/globalnewsmvvmarchitecture/data/api/NewsAPIServiceTest.kt
//
//        //lateinit var newsAPIService : NewsAPIService
//        lateinit var mockWebServer: MockWebServer
//
//        @Before
//        fun setUp() {
//            mockWebServer = MockWebServer()
//            newsAPIService = Retrofit.Builder().baseUrl(mockWebServer.url(""))
//                .addConverterFactory(GsonConverterFactory.create())
//                .build().create(NewsAPIService::class.java)
//        }
//
//        fun enqueueMockUpResponse(fileName: String) {
//            val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
//            val source = inputStream?.source()?.buffer()
//
//            val mockResponse = MockResponse()
//            mockResponse.setBody(source!!.readString(Charsets.UTF_8))
//            mockWebServer.enqueue(mockResponse)
//        }
//
//        @Test
//        fun getTopHeadlinesFakeResponse() {
//            runBlocking {
//                enqueueMockUpResponse("newsApi.json")
//
//                val respose = newsAPIService.getTopHeadlines(country = "us", page = 1).body()
//
//                val predefineResponse = mockWebServer.takeRequest()
//
//                assertThat(respose).isNotNull()
//                assertThat(predefineResponse.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=66e93c8c73034ae7beed54f08e915c47")
//            }
//        }
//
//        @Test
//        fun getTopHeadline_receiveResponse_correctPageSize() {
//            runBlocking {
//                enqueueMockUpResponse("newsApi.json")
//                val respose = newsAPIService.getTopHeadlines(country = "us", page = 1).body()
//                val predefineResponse = respose!!.articles
//                assertThat(predefineResponse.size).isEqualTo(20)
//            }
//        }
//
//        @Test
//        fun getTopHeadline_receiveResponse_getCorrectContent() {
//            runBlocking {
//
//                enqueueMockUpResponse("newsApi.json")
//                val respose = newsAPIService.getTopHeadlines(country = "us", page = 1).body()
//                val predefineResponse = respose!!.articles[0]
//                assertThat(predefineResponse.author).isEqualTo("William K. Rashbaum, Ben Protess, Jonah E. Bromwich")
//                assertThat(predefineResponse.title).isEqualTo("Trump Organization Could Face Criminal Charges in D.A. Inquiry - The New York Times")
//
//            }
//        }
//
//        @After
//        fun destroy() {
//            mockServer.shutdown()
        }



