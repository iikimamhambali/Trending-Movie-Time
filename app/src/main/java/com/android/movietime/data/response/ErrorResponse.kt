package com.android.movietime.data.response

data class ErrorResponse<T>(
    val errorCode: Int,
    val error: Throwable
) : ApiResponse<T>()