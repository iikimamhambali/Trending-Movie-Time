package com.android.movietime.extention

import android.content.Context
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat

fun Context.getDrawableCompat(@DrawableRes drawableId: Int) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.resources.getDrawable(drawableId, null)
    } else AppCompatResources.getDrawable(this, drawableId)

fun Context.getColorCompat(@ColorRes colorId: Int) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        ContextCompat.getColor(this, colorId)
    } else this.resources.getColor(colorId)
