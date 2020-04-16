package com.android.movietime.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreList(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    var isCheck: Boolean = false
) : Parcelable