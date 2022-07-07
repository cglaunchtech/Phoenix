package com.example.sportssocial.data.api

import com.example.sportssocial.BuildConfig
import com.example.sportssocial.data.api.pojo.TopHeadlinesPojo
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Inject

class RetrofitClient @Inject constructor(
    private val retrofitService : RetrofitService
) {

    suspend fun getNews(
        country : String,
        category : String,
        query : String,
        pageSize : Int,
        page : Int
    ) : ApiResponse<TopHeadlinesPojo> =
        retrofitService.getNews(
            country = country,
            category = category,
            query = query,
            pageSize = pageSize,
            page = page
        )
}