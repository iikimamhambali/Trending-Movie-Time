package com.android.movietime.view.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.android.movietime.R
import com.android.movietime.base.BaseActivity
import com.android.movietime.data.entity.DiscoverMovieRequest
import com.android.movietime.view.home.HomeViewModel
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : BaseActivity() {

    private val viewModel by viewModel<HomeViewModel>()

    private var movieId = 0

    companion object {
        const val MOVIE_ID = "MOVIE_ID"
    }

    override fun getLayoutResId(): Int = R.layout.activity_detail_movie

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        getIntentData()
    }

    private fun getIntentData() {
        intent?.extras?.let {
            movieId = it.getInt(MOVIE_ID, 0)
        }
    }

    override fun loadingData(isFromSwipe: Boolean) {
        super.loadingData(isFromSwipe)

        viewModel.getDetailMovie(
            DiscoverMovieRequest(
                getString(R.string.api_key),
                movieId = movieId
            )
        )

        viewModel.getReviewMovie(
            DiscoverMovieRequest(
                getString(R.string.api_key),
                movieId = movieId,
                page = 1
            )
        )

    }

    override fun observeData() {
        super.observeData()
        viewModel.detailMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, _ ->
                toast("Get Detail")
            })
        })

        viewModel.reviewMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, _ ->
                toast("Get Review")
            })
        })
    }
}
