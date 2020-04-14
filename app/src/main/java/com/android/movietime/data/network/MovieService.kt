package com.android.movietime.data.network

import androidx.lifecycle.LiveData
import com.android.movietime.data.entity.DetailMovieResult
import com.android.movietime.data.entity.DiscoverMovieResult
import com.android.movietime.data.entity.GenreResult
import com.android.movietime.data.entity.ReviewResult
import com.android.movietime.data.response.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    fun getDiscoverMovie(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("with_genres") genres: Int = 0
    ): LiveData<ApiResponse<DiscoverMovieResult>>

    @GET("genre/movie/list")
    fun getGenreMovie(
        @Query("api_key") key: String,
        @Query("language") language: String
    ): LiveData<ApiResponse<GenreResult>>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") language: String = "en-US",
        @Query("append_to_response") appendToResponse: String
    ): LiveData<ApiResponse<DetailMovieResult>>

    @GET("movie/{movie_id}/reviews")
    fun getReviewMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): LiveData<ApiResponse<ReviewResult>>
}