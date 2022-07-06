package com.example.sportssocial.data.api

import com.example.sportssocial.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val url = originalUrl.newBuilder()
            .addQueryParameter("keyValue", BuildConfig.KEY)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}