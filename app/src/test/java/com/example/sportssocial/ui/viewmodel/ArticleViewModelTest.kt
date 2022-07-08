package com.example.sportssocial.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sportssocial.TestCoroutineRule
import com.example.sportssocial.data.api.pojo.Article
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.data.repo.AthleteRepository
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.data.repo.NewsArticleRepository
import io.reactivex.rxjava3.core.Observer
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ArticleViewModelTest {
    lateinit var vm: ArticleViewModel

    @Mock
    lateinit var articleList: Observer<List<Article>>

    @Mock
    lateinit var dao: AthleteDao

    @Mock
    lateinit var repo: NewsArticleRepository

    @Mock
    lateinit var app: Application

    @Mock
    lateinit var context: Context

    @get:Rule
    var coroutinesTestRule = TestCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        MockitoAnnotations.openMocks(this)
        this.vm = ArticleViewModel(repo, app)
    }


    private fun setupObservers() {
        articleList = Mockito.mock(Observer::class.java) as Observer<List<Article>>
    }

    @Test
    fun getAllArticles() {
        runBlocking {
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
                    "fullarticle"
                )
            ))

            Mockito.`when`(repo.getAllArticles())
            //    .thenReturn(Observable.fromArray(fakeList))
            vm.getAllArticles()
            Mockito.verify(repo, Mockito.times(1)).getAllArticles()
        }
    }

    @Test
    fun upsertArticle() {
        runBlocking {
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
                    "fullarticle"
                )
            ))

            Mockito.`when`(repo.upsertArticle(NewsArticle(111,
                "New York Times",
                "John Smith",
                "Warriors Win",
                "Game recap",
                "articleurl",
                "imageurl",
                "NYT",
                "fullarticle")))

            //    .thenReturn(Observable.fromArray(fakeList))
            vm.upsertArticle(NewsArticle(111, "", "", "", "", "", "", "", ""))

            Mockito.verify(repo, Mockito.times(1)).upsertArticle(
                article
                = NewsArticle(111, "", "", "", "", "", "", "", "")
            )
        }
    }

    @Test
    fun getNews() {
        runBlocking {
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
                    "fullarticle"
                )
            ))

            Mockito.`when`(repo.getNews())
            //    .thenReturn(Observable.fromArray(fakeList))
            vm.getNews()
            Mockito.verify(repo, Mockito.times(1)).getNews()
        }
    }

    @Test
    fun getArticlebyId() {
        runBlocking {
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
                    "fullarticle"
                )
            ))

            Mockito.`when`(repo.getArticlesbyId(123))
            //    .thenReturn(Observable.fromArray(fakeList))
            vm.getArticlebyId(123)
            Mockito.verify(repo, Mockito.times(1)).getArticlesbyId(123)
        }

        @After
        fun tearDown() {
        }
    }
}



//https://github.com/niharika2810/UnitTesting-MVVM-Kotlin-Koin-Coroutines-Sample/blob/master/app/src/test/java/com/example/unittestingsample/view/ItemViewModelMockTest.kt


//
//        @Mock
//        private lateinit var items: ArrayList<Item>
//
//        @Rule
//        @JvmField
//        var instantExecutorRule = InstantTaskExecutorRule()
//
//        @Rule
//        @JvmField
//        val coRoutineTestRule = CoroutineTestRule()
//
//        //Use android.arch.core:core-testing:version
//        @get:Rule
//        val rule = InstantTaskExecutorRule()
//
//        private val mockObserverForStates = mock<Observer<ItemDataState>>()
//
//        @Mock
//        private lateinit var headers: Headers
//
//        @Before
//        fun before() {
//            itemViewModel = ItemViewModel(serviceUtil).apply {
//                uiState.observeForever(mockObserverForStates)
//            }
//        }
//
//        @Test
//        fun testIfHeadersMissing_Report() {
//            initValues("ClientId", "", "")
//
//            runBlockingTest {
//                `when`(serviceUtil.getList(ArgumentMatchers.anyMap<String, String>() as HashMap<String, String>)).thenReturn(
//                    items
//                )
//
//                itemViewModel.showList(headers)
//
//                verify(mockObserverForStates).onChanged(ItemDataState.Error(ArgumentMatchers.any()))
//                verifyNoMoreInteractions(mockObserverForStates)
//            }
//        }
//
//        @Test
//        fun testIfHeadersValid_FetchListFromServer_ShowSuccess() {
//            initValues("ClientId", "AccessToken", "UserId")
//
//            runBlockingTest {
//                `when`(serviceUtil.getList(ArgumentMatchers.anyMap<String, String>() as HashMap<String, String>)).thenReturn(
//                    items
//                )
//
//                itemViewModel.showList(headers)
//
//                verify(mockObserverForStates).onChanged(ItemDataState.ShowProgress)
//                verify(mockObserverForStates, times(2)).onChanged(
//                    ItemDataState.Success(ArgumentMatchers.any())
//                )
//                verifyNoMoreInteractions(mockObserverForStates)
//            }
//        }
//
//        @Test
//        fun testThrowErrorOnListFetchFailed() {
//            initValues("ClientId", "AccessToken", "UserId")
//
//            runBlocking {
//                val error = IllegalStateException()
//
//                `when`(serviceUtil.getList(ArgumentMatchers.anyMap<String, String>() as HashMap<String, String>)).thenThrow(
//                    error
//                )
//
//                itemViewModel.showList(headers)
//
//                verify(mockObserverForStates).onChanged(ItemDataState.ShowProgress)
//                verify(
//                    mockObserverForStates,
//                    times(2)
//                ).onChanged(ItemDataState.Error(ArgumentMatchers.any()))
//                verifyNoMoreInteractions(mockObserverForStates)
//            }
//        }
//
//        private fun initValues(clientId: String, accessToken: String, userId: String) {
//            `when`(headers.clientId).thenReturn(clientId)
//            `when`(headers.accessToken).thenReturn(accessToken)
//            `when`(headers.userId).thenReturn(userId)
//        }
//
//        private inline fun <reified T> mock(): T = mock(T::class.java)
//
//    }
//}