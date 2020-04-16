package com.android.movietime.data.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.android.movietime.app.AppExecutors
import com.android.movietime.data.response.*
import java.io.IOException

abstract class RepostioryLiveData<Type>(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<Type>>()

    init {
        fetchFromNetwork()
    }

    @MainThread
    private fun setValue(newValue: Resource<Type>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork() {
        val apiResponse = loadFromNetwork()
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {
                is SuccessResponse -> {
                    appExecutors.diskIO().execute {
                        val newResponse = processResponse(response)
                        appExecutors.mainThread().execute {
                            setValue(Resource.success(newResponse))
                        }
                    }
                }
                is ErrorResponse -> {
                    appExecutors.mainThread().execute {
                        when {
                            response.errorCode == ErrorCodeResponse.NOT_FOUND -> {
                                setValue(Resource.empty(response.error))
                            }
                            response.errorCode == ErrorCodeResponse.UNAUTHORIZED -> {
                                setValue(Resource.unAuthorization(response.error))
                            }
                            response.error is IOException -> setValue(
                                Resource.networkFailed(
                                    response.error
                                )
                            )
                            else -> setValue(Resource.error(response.error))
                        }
                    }
                }
            }
        }
    }

    fun asLiveData() = result as LiveData<Resource<Type>>

    @WorkerThread
    protected open fun processResponse(response: SuccessResponse<Type>) = response.body

    @MainThread
    protected abstract fun loadFromNetwork(): LiveData<ApiResponse<Type>>
}