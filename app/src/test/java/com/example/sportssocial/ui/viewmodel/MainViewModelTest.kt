package com.example.sportssocial.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sportssocial.TestCoroutineRule
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.repo.AthleteRepository
import com.example.sportssocial.data.repo.FirestoreRepo
import io.reactivex.rxjava3.core.Observable.fromArray
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.*

class MainViewModelTest {

    lateinit var vm: MainViewModel

    @Mock
    lateinit var athleteList: Observer<List<Athlete>>

    @Mock
    lateinit var repo: FirestoreRepo

    @Mock
    lateinit var dao: AthleteDao

    @Mock
    lateinit var athleteRepo: AthleteRepository

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

        this.vm = MainViewModel(athleteRepo)
    }

    private fun setupObservers() {
        athleteList = Mockito.mock(Observer::class.java) as Observer<List<Athlete>>
    }

    @After
    fun tearDown() {
    }


    @Test
    fun getAllAthleteTest() {
        runBlocking {
            var fakeList: List<Athlete> = (listOf<Athlete>(
                Athlete(
                    234,
                    "123",
                    "myusername",
                    "profilephotourl",
                    "John",
                    "Smith",
                    "Los Angeles",
                    "CA",
                    "01/01",
                    "Things about me",
                    "basketball",
                    "football",
                    listOf(""),
                    listOf(""),
                    listOf("")
                )
            ))
            Mockito.`when`(athleteRepo.getAllAthletes())
                .thenReturn(Observable.fromArray(fakeList))
            vm.getAllAthletes()
            verify(athleteRepo, times(1)).getAllAthletes()

        }
    }
}
//
//    @Test
//        fun insertAthlete() {
//            runBlocking {
//
//                var fakeList: List<Athlete> = (listOf<Athlete>(
//                    Athlete(
//                        234,
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
//                Mockito.`when`(athleteRepo.getAllAthletes())
//                    .thenReturn(arrayOf(Observable()
//            }
//                vm.insertAthlete(athletes = Athlete())
//                Mockito.verify(athleteRepo, Mockito.times(1)).insertAthlete(athletes = Athlete())
//            }
//
//        @Test
//        fun updateAthlete() {
//            runBlocking {
//                var fakeList: List<Athlete> = (listOf<Athlete>(
//                    Athlete(
//                        234,
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
//                Mockito.`when`(athleteRepo.updateAthlete(athletes = Athlete()))
//                    .thenReturn(arrayOf(Observable()
//            }
//                vm.updateAthlete(athlete = Athlete())
//                Mockito.verify(athleteRepo, Mockito.times(1)).updateAthlete(athletes = Athlete())
//            }
//
//        @Test
//        fun deleteAthlete() {
//            runBlocking {
//                var fakeList: List<Athlete> = (listOf<Athlete>(
//                    Athlete(
//                        234,
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
//                        listOf("")
//                    )
//                ))
//
//                Mockito.`when`(athleteRepo.deleteAthlete(athlete = Athlete()))
//                    .thenReturn(athlete)
//                vm.getAllAthletes()
//                verify(athleteRepo,times(1)).getAllAthletes()
//    }
//}
//}



//@Test
//fun `given repository when calling athleteList then list is empty and assert its empty`()


//https://github.com/mockito/mockito-kotlin/blob/main/mockito-kotlin/src/test/kotlin/test/CoroutinesTest.kt

//    @Test
//    fun stubbingSuspending() {
//        /* Given */
//        val m = mock<SomeInterface> {
//            onBlocking { suspending() } doReturn 42
//        }
//
//        /* When */
//        val result = runBlocking { m.suspending() }
//
//        /* Then */
//        expect(result).toBe(42)
//    }
//
//    @Test
//    fun stubbingSuspending_usingSuspendingFunction() {
//        /* Given */
//        val m = mock<SomeInterface> {
//            onBlocking { suspending() } doReturn runBlocking { SomeClass().result(42) }
//        }
//
//        /* When */
//        val result = runBlocking { m.suspending() }
//
//        /* Then */
//        expect(result).toBe(42)
//    }
//
//    @Test
//    fun stubbingSuspending_runBlocking() = runBlocking {
//        /* Given */
//        val m = mock<SomeInterface> {
//            onBlocking { suspending() } doReturn 42
//        }
//
//        /* When */
//        val result = m.suspending()
//
//        /* Then */
//        expect(result).toBe(42)
//    }
//
//    @Test
//    fun stubbingNonSuspending() {
//        /* Given */
//        val m = mock<SomeInterface> {
//            onBlocking { nonsuspending() } doReturn 42
//        }
//
//        /* When */
//        val result = m.nonsuspending()
//
//        /* Then */
//        expect(result).toBe(42)
//    }
//
//    @Test
//    fun stubbingNonSuspending_runBlocking() = runBlocking {
//        /* Given */
//        val m = mock<SomeInterface> {
//            onBlocking { nonsuspending() } doReturn 42
//        }
//
//        /* When */
//        val result = m.nonsuspending()
//
//        /* Then */
//        expect(result).toBe(42)
//    }
//
//    @Test
//    fun delayingResult() {
//        /* Given */
//        val m = SomeClass()
//
//        /* When */
//        val result = runBlocking { m.delaying() }
//
//        /* Then */
//        expect(result).toBe(42)
//    }
//
//    @Test
//    fun delayingResult_runBlocking() = runBlocking {
//        /* Given */
//        val m = SomeClass()
//
//        /* When */
//        val result = m.delaying()
//
//        /* Then */
//        expect(result).toBe(42)
//    }
//
//    @Test
//    fun verifySuspendFunctionCalled() {
//        /* Given */
//        val m = mock<SomeInterface>()
//
//        /* When */
//        runBlocking { m.suspending() }
//
//        /* Then */
//        runBlocking { verify(m).suspending() }
//    }
//
//    @Test
//    fun verifySuspendFunctionCalled_runBlocking() = runBlocking<Unit> {
//        val m = mock<SomeInterface>()
//
//        m.suspending()
//
//        verify(m).suspending()
//    }
//
//    @Test
//    fun verifySuspendFunctionCalled_verifyBlocking() {
//        val m = mock<SomeInterface>()
//
//        runBlocking { m.suspending() }
//
//        verifyBlocking(m) { suspending() }
//    }
//
//    @Test
//    fun verifyAtLeastOnceSuspendFunctionCalled_verifyBlocking() {
//        val m = mock<SomeInterface>()
//
//        runBlocking { m.suspending() }
//        runBlocking { m.suspending() }
//
//        verifyBlocking(m, atLeastOnce()) { suspending() }
//    }
//
//    @Test
//    fun verifySuspendMethod() = runBlocking {
//        val testSubject: SomeInterface = mock()
//
//        testSubject.suspending()
//
//        inOrder(testSubject) {
//            verify(testSubject).suspending()
//        }
//    }
//
//    @Test
//    fun answerWithSuspendFunction() = runBlocking {
//        val fixture: SomeInterface = mock()
//
//        whenever(fixture.suspendingWithArg(any())).doSuspendableAnswer {
//            withContext(Dispatchers.Default) { it.getArgument<Int>(0) }
//        }
//
//        assertEquals(5, fixture.suspendingWithArg(5))
//    }
//
//    @Test
//    fun inplaceAnswerWithSuspendFunction() = runBlocking {
//        val fixture: SomeInterface = mock {
//            onBlocking { suspendingWithArg(any()) } doSuspendableAnswer {
//                withContext(Dispatchers.Default) { it.getArgument<Int>(0) }
//            }
//        }
//
//        assertEquals(5, fixture.suspendingWithArg(5))
//    }
//
//    @Test
//    fun callFromSuspendFunction() = runBlocking {
//        val fixture: SomeInterface = mock()
//
//        whenever(fixture.suspendingWithArg(any())).doSuspendableAnswer {
//            withContext(Dispatchers.Default) { it.getArgument<Int>(0) }
//        }
//
//        val result = async {
//            val answer = fixture.suspendingWithArg(5)
//
//            Result.success(answer)
//        }
//
//        assertEquals(5, result.await().getOrThrow())
//    }
//
//    @Test
//    fun callFromActor() = runBlocking {
//        val fixture: SomeInterface = mock()
//
//        whenever(fixture.suspendingWithArg(any())).doSuspendableAnswer {
//            withContext(Dispatchers.Default) { it.getArgument<Int>(0) }
//        }
//
//        val actor = actor<Optional<Int>> {
//            for (element in channel) {
//                fixture.suspendingWithArg(element.get())
//            }
//        }
//
//        actor.send(Optional.of(10))
//        actor.close()
//
//        verify(fixture).suspendingWithArg(10)
//
//        Unit
//    }
//
//    @Test
//    fun answerWithSuspendFunctionWithoutArgs() = runBlocking {
//        val fixture: SomeInterface = mock()
//
//        whenever(fixture.suspending()).doSuspendableAnswer {
//            withContext(Dispatchers.Default) { 42 }
//        }
//
//        assertEquals(42, fixture.suspending())
//    }
//
//    @Test
//    fun willAnswerWithControlledSuspend() = runBlocking {
//        val fixture: SomeInterface = mock()
//
//        val job = Job()
//
//        whenever(fixture.suspending()).doSuspendableAnswer {
//            job.join()
//            5
//        }
//
//        val asyncTask = async {
//            fixture.suspending()
//        }
//
//        job.complete()
//
//        withTimeout(100) {
//            assertEquals(5, asyncTask.await())
//        }
//    }
//}
//
//interface SomeInterface {
//
//    suspend fun suspending(): Int
//    suspend fun suspendingWithArg(arg: Int): Int
//    fun nonsuspending(): Int
//}
//
//class SomeClass {
//
//    suspend fun result(r: Int) = withContext(Dispatchers.Default) { r }
//
//    suspend fun delaying() = withContext(Dispatchers.Default) {
//        delay(100)
//        42
//    }


//  @Test
//    fun getAllAthletesTest() {
//        runBlocking {
//
//            var fakeList: List<Athlete> = (listOf<Athlete>(
//                Athlete(
//                    234,
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
//                    listOf("")
//                )
//            ))
//
//            Mockito.`when`(athleteRepo.getAllAthletes())
//                .thenRetu
//        }
//            vm.getAllAthletes()
//            Mockito.verify(athleteRepo, Mockito.times(1)).getAllAthletes()
//        }
//}