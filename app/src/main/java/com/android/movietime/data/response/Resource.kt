package com.android.movietime.data.response

data class Resource<out T>(
    val status: StatusState,
    val data: T?,
    val throwable: Throwable? = null
) {

    companion object {

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(StatusState.Loading, data)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(StatusState.Success, data, null)
        }

        fun <T> empty(throwable: Throwable? = null): Resource<T> {
            return Resource(StatusState.NotFound, null, throwable)
        }

        fun <T> unAuthorization(throwable: Throwable? = null): Resource<T> {
            return Resource(StatusState.Unauthorized, null, throwable)
        }

        fun <T> networkFailed(throwable: Throwable? = null): Resource<T> {
            return Resource(StatusState.NetworkFailed, null, throwable)
        }

        fun <T> error(throwable: Throwable? = null): Resource<T> {
            return Resource(StatusState.UnknownError, null, throwable)
        }
    }
}