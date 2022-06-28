package com.example.sportssocial.data.api

import com.example.sportssocial.data.model.db.entities.UserProfile
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface FirestoreDataCalls {
//    @Headers(
//        //TODO Add current users auth key/Id
//    )
    @GET("user")
    suspend fun getUsers(): Response<List<UserProfile>>

    @GET("user/:id")
    suspend fun getUserById(
        @Query("uid") uid: String
    ): Response<UserProfile>

    @POST("user")
    suspend fun newAthlete(@Body user: UserProfile): Response<UserProfile>

    @PUT("user/:id")
    suspend fun updateAthlete(@Body user: UserProfile): Response<UserProfile>



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