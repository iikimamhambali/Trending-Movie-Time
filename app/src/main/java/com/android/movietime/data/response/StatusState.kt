package com.android.movietime.data.response

sealed class StatusState {
    object Loading : StatusState()
    object Success : StatusState()
    object NotFound : StatusState()
    object Unauthorized : StatusState()
    object NetworkFailed : StatusState()
    object UnknownError : StatusState()
}