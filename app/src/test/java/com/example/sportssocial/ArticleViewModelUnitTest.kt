package com.example.sportssocial

import android.app.Application
import com.example.sportssocial.data.api.RetrofitClient
import com.example.sportssocial.data.model.dao.NewsArticleDao
import com.example.sportssocial.data.model.db.entities.NewsArticle
import com.example.sportssocial.data.repo.NewsArticleRepository
import com.example.sportssocial.ui.viewmodel.ArticleViewModel
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

class ArticleViewModelUnitTest {

        lateinit var vm: ArticleViewModel

        @Mock
        lateinit var repo: NewsArticleRepository

        @Mock
        lateinit var articleList: Observer<List<NewsArticle>>

        @Mock
        lateinit var dao: NewsArticleDao

        @Mock
        lateinit var inter: RetrofitClient


        @Before
        fun setUp(){
//        MockitoAnnotations.initMocks(this)
            MockitoAnnotations.openMocks(this)
//        repo = BookRepository(inter, dao)
            vm = ArticleViewModel(app = Application())
//        setupObservers()
        }
        private fun setupObservers(){
            articleList = Mockito.mock(Observer::class.java) as Observer<List<NewsArticle>>
        }

       // @Test
//        fun getAllnewsArticlesTest(){
//            var fakeList :List<NewsArticle> = (listOf<NewsArticle>(
//                NewsArticle(234,"",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "")
//            ))
//
//            Mockito.`when`(repo.getNews())
//                .thenReturn(Observable.fromArray(fakeList))
//
//
//            val result = vm.getNews()
//
//            result.subscribeBy(
//                onNext = {
//                    assertEquals(listOf<NewsArticle>(
//                        NewsArticle(234,"",
//                            "",
//                            "",
//                            "",
//                            "",
//                            "",
//                            "",
//                            "")
//                    )
//                    ),it)
//                },
//                onError = { println("error :$it")}
//            )
//        }

//        @Test
//        fun `given repository when calling athleteList then list is empty and assert its empty`() {
//
//            var fakeList :List<NewsArticle> = (listOf<NewsArticle>(
//                NewsArticle(234,"",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "")
//            ))
//
//            Mockito.`when`(repo.getNews())
//                .thenReturn(Observable.fromArray(fakeList))
//
//
//            val result = vm.getNews()
//
//            result.subscribeBy(
//                onNext = {
//                    assertEquals(listOf<NewsArticle>(
//                        NewsArticle(234,"",
//                            "",
//                            "",
//                            "",
//                            "",
//                            "",
//                            "",
//                            "")
//                    )
//                    ),it)
//                },
//                onError = { println("error :$it")}
//       )
//     }
//}


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