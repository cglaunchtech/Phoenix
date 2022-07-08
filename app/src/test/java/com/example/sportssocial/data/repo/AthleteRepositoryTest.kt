package com.example.sportssocial.data.repo

import android.content.Context
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.db.entities.Athlete
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AthleteRepositoryTest {

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

        MockitoAnnotations.openMocks(this)

        @Test
        fun getAllathleteProfilesfromCloud() {

            var fakeList: List<Athlete> = (listOf<Athlete>(
                Athlete(
                    234,
                    "",
                    "",
                    " ",
                    "",
                    " ",
                    " ",
                    "",
                    " ",
                    " ",
                    " ",
                    " ",
                    listOf(""),
                    listOf(""),
                    listOf("")
                )
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

            @Test
            fun getAllAthletesfromRepo() {
                var fakeList: List<Athlete> = (listOf<Athlete>(
                    Athlete(
                        234,
                        "",
                        "",
                        " ",
                        "",
                        " ",
                        " ",
                        "",
                        " ",
                        " ",
                        " ",
                        " ",
                        listOf(""),
                        listOf(""),
                        listOf("")
                    )
                ))


                Mockito.`when`(athleteRepo.getAllAthletes())
                    .thenReturn(Observable.just(fakeList))

                var result = athleteRepo.getAllAthletes()

                if (result != null) {
                    result.subscribeBy(
                        onNext = {
                            Assert.assertEquals(fakeList, it)
                        },
                        onError = { println("error :$it") }
                    )
                }
            }

//            @Test
//            fun insertAthlete() {
//                var fakeList: List<Athlete> = (listOf<Athlete>(
//                    Athlete(
//                        234,
//                        "123",
//                        "username",
//                        "profilePhoto",
//                        "John",
//                        "Smith ",
//                        "New York ",
//                        "NY",
//                        "01/01 ",
//                        "Information about me.",
//                        " Basketball",
//                        "Baseball ",
//                        listOf("photoCollectionurl"),
//                        listOf("videoCollectionurl"),
//                        listOf("followers")
//                    )
//                ))
//
//
//                Mockito.`when`(athleteRepo.insertAthlete(athletes = Athlete()))
//                    .thenReturn(0)
//
//                var result = athleteRepo.insertAthlete(athletes = Athlete())
//
//                if (result != null) {
//                    result.subscribeBy(
//                        onNext = {
//                            Assert.assertEquals(fakeList, it)
//                        },
//                        onError = { println("error :$it") }
//                    )
//                }
//            }
//
//            @Test
//            fun updateAthleteprofile() {
//                var fakeList: List<Athlete> = (listOf<Athlete>(
//                    Athlete(
//                        234,
//                        "123",
//                        "username",
//                        "profilePhoto",
//                        "John",
//                        "Smith ",
//                        "New York ",
//                        "NY",
//                        "01/01 ",
//                        "Information about me.",
//                        " Basketball",
//                        "Baseball ",
//                        listOf("photoCollectionurl"),
//                        listOf("videoCollectionurl"),
//                        listOf("followers")
//                    )
//                ))
//
//
//                Mockito.`when`(athleteRepo.updateAthlete(athletes = Athlete()))
//                    .thenReturn(Observable.just(fakeList))
//
//                var result = athleteRepo.getAllAthletes()
//
//                if (result != null) {
//                    result.subscribeBy(
//                        onNext = {
//                            Assert.assertEquals(fakeList, it)
//                        },
//                        onError = { println("error :$it") }
//                    )
//                }
//            }
//
//            @Test
//            fun deleteAthlete() {
//            }
//
//            @Test
//            fun findAthletebyUsername() {
//            }
//
//            @Test
//            fun searchAthletes() {
//            }
//            @After
//            fun tearDown() {
            }
        }
    }

