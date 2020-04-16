package com.android.movietime.dependencies.module

import com.android.movietime.data.repository.RepositoryMovie
import org.koin.dsl.module

val repositoryModule = module {

    single { RepositoryMovie(get(), get()) }
}