package com.android.movietime.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewResult(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("page") val page: Int = 0,
    @SerializedName("results") val results: List<ReviewList> = listOf(),
    @SerializedName("total_pages") val totalPages: Int = 0,
    @SerializedName("total_results") val totalResults: Int = 0
) : Parcelable