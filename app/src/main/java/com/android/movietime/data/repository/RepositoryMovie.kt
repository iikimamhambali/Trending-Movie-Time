package com.android.movietime.data.repository

import androidx.lifecycle.LiveData
import com.android.movietime.app.AppExecutors
import com.android.movietime.data.entity.*
import com.android.movietime.data.network.MovieService
import com.android.movietime.data.response.ApiResponse
import com.android.movietime.data.response.Resource

class RepositoryMovie(
    private val appExtractor: AppExecutors,
    private val service: MovieService
) {

    fun getDiscoverMovie(request: DiscoverMovieRequest): LiveData<Resource<DiscoverMovieResult>> {
        return object : RepostioryLiveData<DiscoverMovieResult>(appExtractor) {
            override fun loadFromNetwork(): LiveData<ApiResponse<DiscoverMovieResult>> =
                service.getDiscoverMovie(
                    request.apiKey,
                    "en-US",
                    request.page,
                    request.genre
                )
        }.asLiveData()
    }

    fun getGenreMovie(request: DiscoverMovieRequest): LiveData<Resource<GenreResult>> {
        return object : RepostioryLiveData<GenreResult>(appExtractor) {
            override fun loadFromNetwork(): LiveData<ApiResponse<GenreResult>> =
                service.getGenreMovie(request.apiKey, "en-US")
        }.asLiveData()
    }

    fun getDetailMovie(request: DiscoverMovieRequest): LiveData<Resource<DetailMovieResult>> {
        return object : RepostioryLiveData<DetailMovieResult>(appExtractor) {
            override fun loadFromNetwork(): LiveData<ApiResponse<DetailMovieResult>> =
                service.getDetailMovie(request.movieId, request.apiKey, "en-US", "videos")

        }.asLiveData()
    }

    fun getReviewVideo(request: DiscoverMovieRequest): LiveData<Resource<ReviewResult>> {
        return object : RepostioryLiveData<ReviewResult>(appExtractor) {
            override fun loadFromNetwork(): LiveData<ApiResponse<ReviewResult>> =
                service.getReviewMovie(request.movieId, request.apiKey, "en-US", request.page)
        }.asLiveData()
    }
}