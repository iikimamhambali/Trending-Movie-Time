package com.android.movietime.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.android.movietime.base.BaseViewModel
import com.android.movietime.data.entity.*
import com.android.movietime.data.repository.RepositoryMovie
import com.android.movietime.data.response.Resource

class HomeViewModel(private val repository: RepositoryMovie) : BaseViewModel() {

    private val requestDiscover = MutableLiveData<DiscoverMovieRequest>()
    private val requestGenre = MutableLiveData<DiscoverMovieRequest>()
    private val requestReview = MutableLiveData<DiscoverMovieRequest>()
    private val requestDetail = MutableLiveData<DiscoverMovieRequest>()

    val discoverMovie: LiveData<Resource<DiscoverMovieResult>> = Transformations
        .switchMap(requestDiscover) {
            repository.getDiscoverMovie(it)
        }

    fun getDiscoverMovie(request: DiscoverMovieRequest) {
        requestDiscover.value = request
    }

    val genreMovie: LiveData<Resource<GenreResult>> = Transformations
        .switchMap(requestGenre) {
            repository.getGenreMovie(it)
        }

    fun getGenreMovie(request: DiscoverMovieRequest) {
        requestGenre.value = request
    }

    val reviewMovie: LiveData<Resource<ReviewResult>> = Transformations
        .switchMap(requestReview) {
            repository.getReviewVideo(it)
        }

    fun getReviewMovie(request: DiscoverMovieRequest) {
        requestReview.value = request
    }

    val detailMovie: LiveData<Resource<DetailMovieResult>> = Transformations
        .switchMap(requestDetail) {
            repository.getDetailMovie(it)
        }

    fun getDetailMovie(request: DiscoverMovieRequest) {
        requestDetail.value = request
    }

}