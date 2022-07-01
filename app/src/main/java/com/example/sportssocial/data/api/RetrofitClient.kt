package com.example.sportssocial.data.api

import android.provider.Contacts.SettingsColumns.KEY
import com.example.sportssocial.BuildConfig
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
        var BASE_URL = "https://newsapi.org/"

        fun create(): RetrofitClient {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(RetrofitClient::class.java)
        }
    }
}