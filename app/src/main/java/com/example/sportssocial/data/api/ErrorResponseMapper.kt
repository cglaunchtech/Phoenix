package com.example.sportssocial.data.api

import com.example.sportssocial.util.NewsErrorResponse
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

/**
 * A mapper for mapping [ApiResponse.Failure.Error] response as custom [NewsErrorResponse] instance.
 *
 * @see [ApiErrorModelMapper](https://github.com/skydoves/sandwich#apierrormodelmapper)
 */
object ErrorResponseMapper : ApiErrorModelMapper<NewsErrorResponse> {

    /**
     * maps the [ApiResponse.Failure.Error] to the [NewsErrorResponse] using the mapper.
     *
     * @param apiErrorResponse The [ApiResponse.Failure.Error] error response from the network request.
     * @return A customized [TheMovieErrorResponse] error response.
     */
    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): NewsErrorResponse {
        return NewsErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
    }
}