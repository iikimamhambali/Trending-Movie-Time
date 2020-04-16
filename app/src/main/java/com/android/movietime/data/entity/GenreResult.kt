package com.android.movietime.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreResult(@SerializedName("genres") var genres: List<GenreList>) : Parcelable