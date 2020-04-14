package com.android.movietime.data.entity

data class DiscoverMovieRequest(
    var apiKey: String = "",
    var page: Int = 0,
    var genre: Int = 0,
    var movieId: Int = 0
)