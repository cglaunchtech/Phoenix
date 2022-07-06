package com.example.sportssocial.data.api

import android.app.Application
import android.widget.Toast
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.map
import com.skydoves.sandwich.message
import com.skydoves.sandwich.operators.ApiResponseSuspendOperator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class GlobalResponseOperator<T> constructor(private val application: Application) :
    ApiResponseSuspendOperator<T>() {

    // handle the case when the API request gets a success response.
    //onSuccess is handled in NewsArticleRepository
    override suspend fun onSuccess(apiResponse: ApiResponse.Success<T>) = Unit

    // handles the case when the API request gets an error response.
    // e.g., internal server error.
    override suspend fun onError(apiResponse: ApiResponse.Failure.Error<T>) =
        withContext(Dispatchers.IO) {
            apiResponse.run {
                Timber.d(message())

                // handling error based on status code.
                when (statusCode) {
                    StatusCode.InternalServerError -> toast("InternalServerError")
                    StatusCode.BadGateway -> toast("BadGateway")
                    else -> toast("$statusCode(${statusCode.code}): ${message()}")
                }

                /** maps the [ApiResponse.Failure.Error] to the [NewsErrorResponse] using the mapper. */
                map(ErrorResponseMapper) {
                    Timber.d(message())
                }
            }
        }

    // handle the case when the API request gets a exception response.
    // e.g., network connection error.
    override suspend fun onException(apiResponse: ApiResponse.Failure.Exception<T>) =
        withContext(Dispatchers.Main) {
            apiResponse.run {
                Timber.d(message())
                toast(message())
            }
        }

    private fun toast(message: String) {
        Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
    }
}