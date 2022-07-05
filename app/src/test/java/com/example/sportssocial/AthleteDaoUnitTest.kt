package com.example.sportssocial

import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.repo.AthleteRepository
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.ui.viewmodel.ArticleViewModel
import com.example.sportssocial.ui.viewmodel.MainViewModel
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
            var dao = AthleteDao()
        MockitoAnnotations.openMocks(this)
//        repo = BookRepository(inter, dao)
        vm = MainViewModel()
//        setupObservers()


    @Test
        fun getAllAthletesfromCloud() {

        var fakeList :List<Athlete> = (listOf<Athlete>(
            Athlete(123,
                    "",
                    "",
                    "",
                    "", "",
                    "", "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "", "")
                ))

                // mock the function call to the api
                Mockito.`when`(())
                    .thenReturn(Single.just(fakeList))

                var result = repo.getAllProfiles()

                result.subscribeBy(
                    onNext = {
                        Assert.assertEquals(fakeList, it)
                    },
                    onError = { println("error :$it")}
                )
            }

            //Response object - normal list
            @Test
            fun insertAthleteTest(){
                var fakeList :List<Athlete> = (listOf<Athlete>(
                    Athlete("",
                        "",
                        "","",
                        "", "",
                        "", "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "", "")
                )
                        ))
                // mock the function call to the api
                Mockito.`when`(athleteRepo.insertAthlete()
                    .thenReturn(Response.success(fakeList))

                    var response = athleteRepo.insertAthlete()

                Assert.assertEquals(fakeList, response.body())

            }

            //coroutines with response object
            @Test
            fun getAllUsersTest(){
                runBlocking {
                    var fakeList :List<Athlete> = (listOf<Athlete>(
                        Athlete("",
                            "",
                            "","",
                            "", "",
                            "", "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "", "")
                    )
                            )
                    // mock the function call to the api
                    Mockito.`when`(repo.getAllProfiles())
                        .thenReturn(Response.success(fakeList))

                    var response = repo.getAllProfiles()

                    Assert.assertEquals(fakeList, response.body())
                }
        @Test

                fun searchAthleteTest(){
                    var fakeList :List<Athlete> = (listOf<Athlete>(
                        Athlete("",
                            "",
                            "","",
                            "", "",
                            "", "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "", "")
                    )
                            ))
                    // mock the function call to the api
                    Mockito.`when`(athleteRepo.searchAthletes()
                        .thenReturn(Response.success(fakeList))

                        var response = athleteRepo.searchAthletes()

                    Assert.assertEquals(fakeList, response.body())

                }
            }

        @Test

        fun updateAthleteTest(){
            var fakeList :List<Athlete> = (listOf<Athlete>(
                Athlete("",
                    "",
                    "","",
                    "", "",
                    "", "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "", "")
            )
                    ))
            // mock the function call to the api
            Mockito.`when`(athleteRepo.updateAthlete()
                .thenReturn(Response.success(fakeList))

                var response = athleteRepo.updateAthlete()

            Assert.assertEquals(fakeList, response.body())

        }
    }
}