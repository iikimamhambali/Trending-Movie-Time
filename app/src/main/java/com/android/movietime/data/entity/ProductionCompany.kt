package com.android.movietime.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductionCompany(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("logo_path") val logoPath: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("origin_country") val originCountry: String? = null
) : Parcelable