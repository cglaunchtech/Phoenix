package com.example.sportssocial.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sportssocial.TestCoroutineRule
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.repo.AthleteRepository
import com.example.sportssocial.data.repo.FirestoreRepo
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

class MainViewModelTest {

    lateinit var vm: MainViewModel


    @Mock
    lateinit var athleteList: Observer<List<Athlete>>

    @Mock
    lateinit var repo: FirestoreRepo

    @Mock
    lateinit var dao: AthleteDao

    @Mock
    lateinit var Athleterepo: AthleteRepository

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

        this.vm = MainViewModel(app)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getAllAthletes() {
        runBlocking {
            vm.getAllAthletes()
            Mockito.verify(Athleterepo, Mockito.times(1)).getAllAthletes()
        }
    }

    @Test
    fun testGetAllAthletes() {
    }

    @Test
    fun insertAthlete() {
    }

    @Test
    fun updateAthlete() {
    }

    @Test
    fun deleteAthlete() {
    }
}