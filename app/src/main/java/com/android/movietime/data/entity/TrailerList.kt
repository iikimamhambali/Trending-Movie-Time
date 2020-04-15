package com.android.movietime.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrailerList(
    @SerializedName("id") var id: String,
    @SerializedName("iso_639_1") var iso_639_1: String,
    @SerializedName("iso_3166_1") var iso_3166_1: String,
    @SerializedName("key") var key: String,
    @SerializedName("name") var name: String,
    @SerializedName("site") var site: String,
    @SerializedName("size") var size: String,
    @SerializedName("type") var type: String
) : Parcelable