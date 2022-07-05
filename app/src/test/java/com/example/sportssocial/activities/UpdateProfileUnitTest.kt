package com.example.sportssocial.activities

import androidx.room.Update
import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.repo.AthleteRepository
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.ui.view.SignUp
import com.example.sportssocial.ui.view.UpdateProfile
import com.example.sportssocial.ui.viewmodel.MainViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UpdateProfileUnitTest {

    lateinit var updateProfile: UpdateProfile

    @Mock
    lateinit var repo: FirestoreRepo

    @Mock
    lateinit var athleteRepo: AthleteRepository


    @Mock
    lateinit var dao: AthleteDao

    @Mock
    lateinit var vm: MainViewModel


    @Before
    fun setUp(){
//        MockitoAnnotations.initMocks(this)
        MockitoAnnotations.openMocks(this)
//        repo = BookRepository(inter, dao)
        updateProfile = UpdateProfile()
//        setupObservers()
    }


    @Test

    fun `empty username returns false` () {
        val result = UpdateProfile (
//What function from Signup page?
        )
    }
}

    @Test

    fun `incorrect password returns false` () {
        val result = UpdateProfile (
//What function from Signup page?
        )
    }
}

@Test

fun `invalid email returns false` () {
    val result = UpdateProfile (
//What function from Signup page?
    )
}
}

@Test

fun `empty first name returns false` () {
    val result = UpdateProfile (
//What function from Signup page?
    )
}
}

@Test

fun `empty last name returns false` () {
    val result = UpdateProfile (
//What function from Signup page?
    )
}


@Test

fun `empty city returns false` () {
    val result = SignUp (
//What function from Signup page?
    )
}
}

@Test

fun `empty state returns false` () {
    val result = UpdateProfile (
//What function from Signup page?
    )
}
}

@Test

fun `empty sport returns false` () {
    val result = UpdateProfile (
//What function from Signup page?
    )
}
