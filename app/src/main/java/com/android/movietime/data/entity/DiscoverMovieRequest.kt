package com.android.movietime.data.entity

data class DiscoverMovieRequest(
    var apiKey: String = "",
    var page: Int = 0,
    var genre: String = "",
    var movieId: Int = 0
)