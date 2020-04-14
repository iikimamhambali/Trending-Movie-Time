package com.android.movietime.data.network

import androidx.lifecycle.LiveData
import com.android.movietime.data.response.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

//    @GET("discover/movie")
//    fun getDiscoverMovie(
//        @Query("api_key") key: String,
//        @Query("language") language: String = "en-US",
//        @Query("page") page: Int,
//        @Query("with_genres") genres: Int
//    ) :LiveData<ApiResponse<Discover>>
}