package com.example.sportssocial.activities

import com.example.sportssocial.data.model.dao.AthleteDao
import com.example.sportssocial.data.repo.AthleteRepository
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.ui.view.LoginActivity
import com.example.sportssocial.ui.view.SignUp
import com.example.sportssocial.ui.viewmodel.MainViewModel
import com.google.common.truth.ExpectFailure.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)

class LoginUnitTest {




        @Test
        fun `login with correct login and password`() {
            //given


        }

        @Test
        fun `login with incorrect login`() {
            //given
            val login = "CORRECT_LOGIN"
            val password = "anyPassword"
            //when
            val result = objectUnderTest.login(login, password)
            //then
            result.test().await()
                .assertResult(false)
        }

        @Test
        fun `do not login with only correct password`() {
            //given
            val login = "anyLogin"
            val password = CORRECT_PASSWORD
            //when
            val result = objectUnderTest.login(login, password)
            //then
            result.test().await()
                .assertResult(false)
        }
       // https://github.com/dbacinski/Android-Testing-With-Kotlin/blob/master/app/src/test/kotlin/com/example/unittesting/login/model/LoginRepositoryTest.kt
        class LoginValidatorTest {

            val objectUnderTest = LoginValidator()

            @Test
            fun `empty login is invalid`() {
                //when
                val result = objectUnderTest.validateLogin("")
                //then
                assertThat(result).isFalse()
            }

            @Test
            fun `not empty login is valid`() {
                //when
                val result = objectUnderTest.validateLogin("anyLogin")
                //then
                assertThat(result).isTrue()
            }

            @Test
            fun `empty password is invalid`() {
                //when
                val result = objectUnderTest.validatePassword("")
                //then
                assertThat(result).isFalse()
            }

            @Test
            fun `password is invalid if shorter then limit`() {
                //when
                val result = objectUnderTest.validatePassword("12345")
                //then
                assertThat(result).isFalse()
            }

            @Test
            fun `password is valid if equal to limit`() {
                //when
                val result = objectUnderTest.validatePassword("123456")
                //then
                assertThat(result).isTrue()
            }

            @Test
            fun `password is valid if longer than limit`() {
                //when
                val result = objectUnderTest.validatePassword("1234567")
                //then
                assertThat(result).isTrue()
            }
        }
    }

}