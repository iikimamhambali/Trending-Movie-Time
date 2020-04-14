package com.android.movietime.data.factory

import androidx.lifecycle.LiveData
import com.android.movietime.data.response.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class LiveDataCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, LiveData<ApiResponse<R>>> {

    companion object {
        const val UNKNOWN_CODE = -1
    }

    override fun adapt(call: Call<R>): LiveData<ApiResponse<R>> {
        return object : LiveData<ApiResponse<R>>() {
            private var isSuccess = false

            override fun onActive() {
                super.onActive()
                if (!isSuccess) enqueue()
            }

            override fun onInactive() {
                super.onInactive()
                dequeue()
            }

            private fun dequeue() {
                if (call.isExecuted) call.cancel()
            }

            private fun enqueue() {
                try {
                    call.enqueue(object : Callback<R> {
                        override fun onFailure(call: Call<R>, t: Throwable) {
                            postValue(ApiResponse.create(UNKNOWN_CODE, t))
                        }

                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(ApiResponse.create(response))
                            isSuccess = true
                        }
                    })
                } catch (e: Exception) {
                }
            }
        }
    }

    override fun responseType(): Type = responseType
}