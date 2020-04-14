package com.android.movietime.dependencies.module

import com.android.movietime.app.AppExecutors
import org.koin.dsl.module

val appModule = module {

    single { AppExecutors() }
}