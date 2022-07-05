package com.example.sportssocial

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.test.core.app.ApplicationProvider
import com.example.sportssocial.data.api.RetrofitClient
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.model.dao.NewsArticleDao
import com.example.sportssocial.data.model.db.entities.Athlete
import com.example.sportssocial.data.repo.AthleteRepository
import com.example.sportssocial.data.repo.NewsArticleRepository
import com.example.sportssocial.ui.viewmodel.MainViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MainViewModelTest {

    lateinit var athleteRepo: AthleteRepository
    lateinit var vm: MainViewModel

    @Mock
    lateinit var retro: RetrofitClient
    @Mock
    lateinit var athleteDao: AthleteDao
    @Mock
    lateinit var allAthletex: LiveData<List<Athlete>>

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        val context = ApplicationProvider.getApplicationContext<Context>()
        vm = MainViewModel(Application())
        athleteRepo = AthleteRepository(context)
    }

    @Test
    fun getAllAthletesTest(){
        runBlocking {   var testAthlete = Athlete(345,"345","userName345","http://www.picture.com/",
            "John","Doe","bigCity", "aState","01/01/01","Hello, I love sports",
            "Basketball","Baseball",listOf("pic1","pic2","pic3"),
            listOf("vid1","vid2","vid3"),listOf("profile1","profile2"))

            Mockito.`when`(athleteRepo.getAllAthletes()).thenReturn(allAthletex)


            val result = vm.getAllAthletes()
            //var actuals = listOf<Athlete>()

            result.test()
                .assertResult(
                    listOf<Athlete>(
                        Athlete(345,"345","userName345","http://www.picture.com/",
                            "John","Doe","bigCity", "aState","01/01/01","Hello, I love sports",
                            "Basketball","Baseball",listOf("pic1","pic2","pic3"),
                            listOf("vid1","vid2","vid3"),listOf("profile1","profile2"))
                    )
                ) }
    }


//    @Test
//    fun getAllUsersTest(){
//        runBlocking {
//            var fakeList :List<Users> = (listOf<Users>(
//                Users(213,"fromtest","","")
//            ))
//            // mock the function call to the api
//            Mockito.`when`(inter.getAllUsers())
//                .thenReturn(success(fakeList))
//
//            var response = repo.getAllUsers()
//
//            assertEquals(fakeList, response.body())
//        }
//    }

//    @Test
//    fun getAllStudentsTest(){
//        var fakeList :List<Students> = (listOf<Students>(
//            Students(234,"fromtest","")
//        ))
//
//        // mock the function call to the api
//        Mockito.`when`(dao.selectStudents())
//            .thenReturn(MutableLiveData(fakeList))
//
//        var result = repo.getAllStudents()
//        assertEquals(result?.value,fakeList)
//    }


    @Test
    fun insertAthleteTest(athletes: Athlete){}

    @Test
    fun updateAthleteTest(athlete: Athlete){}

    @Test
    fun deleteAthleteTest(athlete: Athlete){}

    @Test
    fun findAthletebyUsernameTest(search: String){}
}