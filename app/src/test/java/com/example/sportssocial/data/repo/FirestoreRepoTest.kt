package com.example.sportssocial.data.repo

import android.content.Context
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.ui.viewmodel.MainViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.channels.ChannelResult.Companion.success
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.util.*
import kotlin.Result.Companion.success

class FirestoreRepoTest {

    lateinit var repo: FirestoreRepo

    @Mock
    lateinit var dao: AthleteDao

    @Mock
    lateinit var vm: MainViewModel

    @Mock
    lateinit var context: Context

    @Before
    fun setup() {
        repo = FirestoreRepo()
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {
    }

   @Test
    fun getAllProfiles() {
        runBlocking {
            var fakeList :List<Athlete> = (listOf<Athlete>(
                Athlete(234,"123", "myusername", "profilephotourl", "John", "Smith",
                    "Los Angeles","CA", "01/01", "Things about me", "basketball",
                    "football", listOf(""), listOf(""),listOf(""))
            ))

            Mockito.`when`(repo.getAllProfiles())
                .thenReturn(Observable.fromArray(fakeList))

            var result = repo.getAllProfiles()

            result.subscribeBy(
                onNext = {
                    Assert.assertEquals(fakeList, it)
                },
                onError = { println("error :$it") }
            )

        }
    }

//    @Test
//    fun getProfileByUsername() {
//        runBlocking {
//            var fakeList :List<Athlete> = (listOf<Athlete>(
//                Athlete(234,
//                    "123",
//                    "myusername",
//                    "profilephotourl",
//                    "John",
//                    "Smith",
//                    "Los Angeles",
//                    "CA",
//                    "01/01",
//                    "Things about me",
//                    "basketball",
//                    "football",
//                    listOf(""),
//                    listOf(""),
//                    listOf(""))
//
//            ))
//
//            Mockito.`when`(repo.getProfileByUsername())
//                .thenReturn(Observable.success(fakeList))
//
//            var response = repo.getProfileByUsername())
//
//            assertEquals(fakeList, Observable())
//        }
//    }
//
//    @Test
//    fun newProfile() {
//    }


//    Mockito.`when`((repo.getProfileByUsername()))
//    .thenReturn(Observable.just(fakeList))
//
//    var result = repo.getAllProfiles()
//
//    result.subscribeBy(
//    onNext = {
//        Assert.assertEquals(fakeList, it)
//    },
//    onError = { println("error :$it") }
//    )
}
