package com.android.movietime.data.response

data class SuccessResponse<T>(val body: T) : ApiResponse<T>()