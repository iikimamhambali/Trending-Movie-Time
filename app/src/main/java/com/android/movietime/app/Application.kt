package com.android.movietime.app

import com.android.movietime.base.BaseApplication
import com.android.movietime.dependencies.libraries
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : BaseApplication() {

    override fun initApplication() {
        startKoin {
            modules(libraries)
            androidContext(this@Application)
        }
    }
}