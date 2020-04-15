package com.android.movietime.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailMovieResult(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("adult") val adult: Boolean = false,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("belongs_to_collection") val belongsToCollection: BelongsCollection? = null,
    @SerializedName("budget") val budget: Int = 0,
    @SerializedName("genres") val genres: List<GenreList> = listOf(),
    @SerializedName("homepage") val homepage: String? = null,
    @SerializedName("imdb_id") val imdbId: String? = null,
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("popularity") val popularity: Double = 0.0,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompany> = listOf(),
    @SerializedName("production_countries") val productionCountries: List<ProductionCountry> = listOf(),
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("revenue") val revenue: Int = 0,
    @SerializedName("runtime") val runtime: Int = 0,
    @SerializedName("spoken_languages") val spokenLanguages: MutableList<SpokenLanguage> = mutableListOf(),
    @SerializedName("status") val status: String? = null,
    @SerializedName("tagline") val tagLine: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("video") val video: Boolean = false,
    @SerializedName("videos") val videos: VideoTrailer,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int = 0
) : Parcelable