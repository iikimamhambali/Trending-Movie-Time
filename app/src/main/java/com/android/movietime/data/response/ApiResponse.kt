package com.android.movietime.data.response

import retrofit2.Response
import java.lang.RuntimeException

open class ApiResponse<T> {

    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            val body = response.body()
            return when {
                response.isSuccessful -> {
                    body?.let { SuccessResponse(it) }!!
                }
                else -> {
                    ErrorResponse(
                        response.code(),
                        RuntimeException(response.raw().networkResponse()?.message())
                    )
                }
            }
        }

        fun <T> create(errorCode: Int, error: Throwable): ErrorResponse<T> {
            return ErrorResponse(errorCode, error)
        }
    }
}