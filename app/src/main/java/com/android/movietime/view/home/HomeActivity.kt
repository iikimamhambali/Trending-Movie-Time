package com.android.movietime.view.home

import androidx.lifecycle.Observer
import com.android.movietime.R
import com.android.movietime.base.BaseActivity
import com.android.movietime.data.entity.DiscoverMovieRequest
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {

    private val viewModel by viewModel<HomeViewModel>()

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun loadingData(isFromSwipe: Boolean) {
        super.loadingData(isFromSwipe)
        viewModel.getDiscoverMovie(DiscoverMovieRequest(getString(R.string.api_key), 1, 28))

        viewModel.getGenreMovie(DiscoverMovieRequest(getString(R.string.api_key)))
    }

    override fun observeData() {
        super.observeData()
        viewModel.discoverMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, _ ->
                toast("Success")
            })
        })

        viewModel.genreMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, _ ->
                toast("Get genre")
            })
        })
    }

}
