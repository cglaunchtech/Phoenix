package com.example.sportssocial.activities

import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.dao.NewsArticleDao
import com.example.sportssocial.data.repo.AthleteRepository
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.data.repo.NewsArticleRepository
import com.example.sportssocial.ui.view.LoginActivity
import com.example.sportssocial.ui.view.MainActivity
import com.example.sportssocial.ui.viewmodel.ArticleViewModel
import com.example.sportssocial.ui.viewmodel.MainViewModel
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class HomepageUnitTest {

        lateinit var homepage: MainActivity

        @Mock
        lateinit var repo: FirestoreRepo

        @Mock
        lateinit var athleteRepo: AthleteRepository

        @Mock
        lateinit var dao: AthleteDao

        @Mock
        lateinit var vm: MainViewModel

        @Mock
        lateinit var newsArticleRepository: NewsArticleRepository

        @Mock
        lateinit var newsArticledao: NewsArticleDao

        @Mock
        lateinit var newsArticleViewModel: ArticleViewModel

        @Before
        fun setUp(){
//        MockitoAnnotations.initMocks(this)
            MockitoAnnotations.openMocks(this)
//        repo = BookRepository(inter, dao)
            homepage = MainActivity()
//        setupObservers()
        }

        val objectUnderTest = LoginActivity()
}