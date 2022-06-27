package com.example.sportssocial.data.api

import com.example.sportssocial.BuildConfig
import com.example.sportssocial.data.model.UserProfile
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FirestoreDataCalls {
    @Headers(
        //TODO Add current users auth key/Id
    )
    @GET("users")
    suspend fun getUsers(): Response<List<UserProfile>>

    @GET("user/:id")
    suspend fun getUserById(
        @Query("uid") uid: String
    ): Response<UserProfile>

    @GET("user/video")
    suspend fun getAllVideoUrls(): Response<List<String>>

    companion object {
        var BASE_URL = "https://us-central1-sportssocial-ec88e.cloudfunctions.net/"

        fun create(): FirestoreDataCalls {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(FirestoreDataCalls::class.java)
        }
    }
}