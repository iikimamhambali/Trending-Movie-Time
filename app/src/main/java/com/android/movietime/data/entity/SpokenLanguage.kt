package com.android.movietime.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpokenLanguage(
    @SerializedName("iso_639_1") val iso: String? = null,
    @SerializedName("name") val name: String? = null
) : Parcelable