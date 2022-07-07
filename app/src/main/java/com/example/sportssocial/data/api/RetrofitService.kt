package com.example.sportssocial.data.api

import com.example.sportssocial.BuildConfig
import com.example.sportssocial.data.api.pojo.TopHeadlinesPojo
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {
    @Headers(
        BuildConfig.KEY
    )
    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String, @Query("category") category: String,
        @Query("q") query: String, @Query("pageSize") pageSize: Int, @Query("page") page: Int
    ): ApiResponse<TopHeadlinesPojo>
}
