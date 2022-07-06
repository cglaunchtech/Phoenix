package com.example.sportssocial

import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.repo.AthleteRepository
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.ui.viewmodel.MainViewModel
import com.google.firebase.database.Transaction.success
import io.reactivex.rxjava3.core.Single
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
import java.util.*
import kotlin.Result.Companion.success

@RunWith(JUnit4::class)

class AthleteDaoUnitTest {

    lateinit var dao: AthleteDao

    @Mock
    lateinit var athleteRepo: AthleteRepository

    @Mock
    lateinit var repo: FirestoreRepo

    @Mock
    lateinit var vm: MainViewModel

    @Before()
    fun setup() {

    }
}
//
//    MockitoAnnotations.openMocks(this)
//     dao = AthleteDao
//
//    @Test
//    fun getAllAthletesfromCloud() {
//    var fakeList :List<Athlete> = (listOf<Athlete>(
//         Athlete(234,
//             "123",
//             "myusername",
//             "profilephotourl",
//             "John",
//             "Smith",
//             "Los Angeles",
//             "CA",
//             "01/01",
//             "Things about me",
//             "basketball",
//             "football",
//             listOf(""),
//             listOf(""),
//             listOf(""))
//            ))
//
//            Mockito.`when`((dao.selectAthlete()))
//                .thenReturn(listOf(Observable))
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
//        @Test
//        fun insertAthleteTest(){
//            var fakeList :List<Athlete> = (listOf<Athlete>(
//            Athlete(234,
//                "123",
//                "myusername",
//                "profilephotourl",
//                "John",
//                "Smith",
//                "Los Angeles",
//                "CA",
//                "01/01",
//                "Things about me",
//                "basketball",
//                "football",
//                listOf(""),
//                listOf(""),
//                listOf(""))
//            ))
//
//            Mockito.`when`(athleteRepo.insertAthlete(athletes = Athlete())
//                .thenReturn(Observable.success(fakeList)))
//
//                var response = athleteRepo.insertAthlete(athletes = Athlete())
//                Assert.assertEquals(fakeList, Observable.body())
//        }
//
//        @Test
//        fun getAllUsersTest(){
//            runBlocking {
//                var fakeList :List<Athlete> = (listOf<Athlete>(
//                    Athlete(234,
//                        "123",
//                        "myusername",
//                        "profilephotourl",
//                        "John",
//                        "Smith",
//                        "Los Angeles",
//                        "CA",
//                        "01/01",
//                        "Things about me",
//                        "basketball",
//                        "football",
//                        listOf(""),
//                        listOf(""),
//                        listOf(""))
//                ))
//
//                Mockito.`when`(repo.getAllProfiles())
//                    .thenReturn(Observable<List<Athlete>>(fakeList))
//
//                var response = repo.getAllProfiles()
//
//                Assert.assertEquals(fakeList, Observable())
//            }
//            @Test
//            fun searchAthleteTest(){
//                var fakeList :List<Athlete> = (listOf<Athlete>(
//                    Athlete(234,
//                        "123",
//                        "myusername",
//                        "profilephotourl",
//                        "John",
//                        "Smith",
//                        "Los Angeles",
//                        "CA",
//                        "01/01",
//                        "Things about me",
//                        "basketball",
//                        "football",
//                        listOf(""),
//                        listOf(""),
//                        listOf(""))
//
//                ))
//
//                Mockito.`when`(athleteRepo.searchAthletes("")
//                .thenReturn(Response.success(fakeList)))
//
//                var response = athleteRepo.searchAthletes("")
//                Assert.assertEquals(fakeList, response.body())
//            }
//        }
//
//        @Test
//        fun updateAthleteTest(){
//            var fakeList :List<Athlete> = (listOf<Athlete>(
//                Athlete(123,
//                    "123",
//                    "fakename",
//                    "photurl",
//                    "Test",
//                    "Case",
//                    "New York",
//                    "NY",
//                    "01/01",
//                    "Something about me",
//                    "soccer",
//                    "basketball",
//                    listOf(""),
//                    listOf(""),
//                    listOf(""))
//                    ))
//
//            Mockito.`when`(athleteRepo.updateAthlete(athletes = Athlete())
//                .thenReturn(Response.success(fakeList)))
//                var response = athleteRepo.updateAthlete(athletes = Athlete())
//                Assert.assertEquals(fakeList, response.body())
//
//        }
//    }
//}