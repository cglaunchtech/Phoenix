package com.example.sportssocial.data.api

import com.example.sportssocial.BuildConfig
import com.example.sportssocial.data.api.pojo.TopHeadlinesPojo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitClient {
    @Headers(
        BuildConfig.KEY
    )
    @GET("v2/top-headlines")
    fun getNews(
        @Query("country") country: String, @Query("category") category: String,
        @Query("q") query: String, @Query("pageSize") pageSize: Int, @Query("page") page: Int
    ): Observable<TopHeadlinesPojo>

    companion object {
        private val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        var BASE_URL = "https://newsapi.org/"

        fun create(): RetrofitClient {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()

            return retrofit.create(RetrofitClient::class.java)
        }
    }
}