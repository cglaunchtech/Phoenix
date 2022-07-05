package com.example.sportssocial

import android.content.Context
import com.example.sportssocial.data.api.RetrofitClient
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.dao.NewsArticleDao
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.repo.AthleteRepository
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.ui.viewmodel.MainViewModel
import io.reactivex.rxjava3.core.Observable.just
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.Single.just
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.util.*

@RunWith(JUnit4::class)
class AthleteRepositoryUnitTest {

    lateinit var athleteRepo: AthleteRepository

    @Mock

    lateinit var dao: AthleteDao

    @Mock
    lateinit var repo: FirestoreRepo

    @Mock
    lateinit var context: Context


    @Before()
    fun setup() {
        athleteRepo = AthleteRepository(context)

        var fakeList: List<Athlete> = (listOf<Athlete>(

            Athlete(
                234, "",
                "",
                "",
                "", "",
                "", "",
                "", "",
                "",
                "",
                listOf(""),
                listOf(""),
                listOf("")
            )))

             //   Mockito.`when`(context.getAllathleteProfiles)


    @Test
    fun getAllathleteProfiles() {

        var fakeList: List<Athlete> = (listOf<Athlete>(
            Athlete(
                234, "",
                "",
                "",
                "", "",
                "", "",
                "", "",
                "",
                "",
                listOf(""),
                listOf(""),
                listOf(""))

        ))

       Mockito. `when` (repo.getAllProfiles())
           .thenReturn(Observable.just(fakeList))

        var result = repo.getAllProfiles()

        result.subscribeBy(
            onNext = {
                Assert.assertEquals(fakeList, it)
            },
            onError = {println("error :$it")}
        )




//    @Test
//    fun getAllAthletesfromCloud() {
//
//            var fakeList :List<Athlete> = (listOf<Athlete>(
//                Athlete("",
//                    "",
//                    "",
//                    "",
//                    "", "",
//                    "", "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "", ""))
//
//                    ))
//
//            // mock the function call to the api
//            Mockito.`when`(())
//                .thenReturn(Single.just(fakeList))
//
//            var result = repo.getAllProfiles()
//
//            result.subscribeBy(
//                onNext = {
//                    Assert.assertEquals(fakeList, it)
//                },
//                onError = { println("error :$it")}
//            )
//        }
//
//        //Response object - normal list
//        @Test
//        fun getAllAthleteprofilesTest(){
//            var fakeList :List<Athlete> = (listOf<Athlete>(
//                Athlete("",
//                    "",
//                    "","",
//                    "", "",
//                    "", "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "", ""))
//            ))
//            // mock the function call to the api
//            Mockito.`when`(repo.getAllProfiles()
//                .thenReturn(Response.success(fakeList))
//
//            var response = repo.getAllProfiles()
//
//            Assert.assertEquals(fakeList, response.body())
//
//        }
//
//        //coroutines with response object
//        @Test
//        fun getAllUsersTest(){
//            runBlocking {
//                var fakeList :List<Athlete> = (listOf<Athlete>(
//                    Athlete("",
//                        "",
//                        "","",
//                        "", "",
//                        "", "",
//                        "",
//                        "",
//                        "",
//                        "",
//                        "",
//                        "",
//                        "", ""))
//                )
//                // mock the function call to the api
//                Mockito.`when`(repo.getAllProfiles())
//                    .thenReturn(Response.success(fakeList))
//
//                var response = repo.getAllProfiles()
//
//                Assert.assertEquals(fakeList, response.body())
//            }
        }
    }
}