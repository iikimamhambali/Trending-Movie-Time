package com.android.movietime.view.detail

import android.content.ActivityNotFoundException
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import com.android.movietime.R
import com.android.movietime.base.BaseActivity
import com.android.movietime.base.BaseRecyclerView
import com.android.movietime.data.entity.DetailMovieResult
import com.android.movietime.data.entity.DiscoverMovieRequest
import com.android.movietime.data.entity.ReviewList
import com.android.movietime.data.entity.TrailerList
import com.android.movietime.extention.getColorCompat
import com.android.movietime.extention.loadFromUrlWithPlaceholder
import com.android.movietime.view.detail.adapter.TrailerAdapter
import com.android.movietime.view.detail.adapter.TrailerViewHolder
import com.android.movietime.view.home.HomeViewModel
import com.android.movietime.view.home.adapter.ReviewAdapter
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.layout_toolbar_default.*
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : BaseActivity(), TrailerViewHolder.SetOnClickTrailer {

    private val viewModel by viewModel<HomeViewModel>()

    private var resultList = mutableListOf<TrailerList>()
    private val adapterTrailer by lazy { TrailerAdapter(resultList, this) }

    private var resultReview = mutableListOf<ReviewList>()
    private val adapterReview by lazy { ReviewAdapter(resultReview) }

    private var movieId = 0

    companion object {
        const val MOVIE_ID = "MOVIE_ID"
    }

    override fun getLayoutResId(): Int = R.layout.activity_detail_movie

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        getIntentData()
        setupToolbar()
        toolbarDetail.bringToFront()
        setupRecyclerView()

    }

    override fun initEvent() {
        super.initEvent()
        setOnClickToolbar()
    }

    private fun getIntentData() {
        intent?.extras?.let {
            movieId = it.getInt(MOVIE_ID, 0)
        }
    }

    private fun setupToolbar() {
        ivToolbarBack.visibility = View.VISIBLE
    }

    private fun setOnClickToolbar() {
        ivToolbarBack.setOnClickListener { finish() }
    }

    private fun setupContent(data: DetailMovieResult) {
        val image = "https://image.tmdb.org/t/p/w780/" + data.backdropPath

        ivMoviePoster.loadFromUrlWithPlaceholder(
            image,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
        )
        tvRating.text = data.voteAverage.toString()
        tvImb.text = data.imdbId
        tvCount.text = data.voteCount.toString()

        tvTitle.text = data.title
        tvDescription.text = data.overview

        setupTrailer(data.videos.list[0])
    }

    private fun setupRecyclerView() {
        with(rvTrailer) {
            initRecyclerView(adapterTrailer, BaseRecyclerView.LayoutManager.VERTICAL)
        }

        with(rvReview) {
            initRecyclerView(adapterReview, BaseRecyclerView.LayoutManager.VERTICAL)
        }
    }

    private fun setupTrailer(items: TrailerList) {
        val urlThumbnail = items.key
        ivTrailer.loadFromUrlWithPlaceholder(
            getString(R.string.link_youtube_image, urlThumbnail),
            R.drawable.ic_launcher_background, R.drawable.ic_launcher_background
        )
        ivTrailer.setOnClickListener {
            val urlPlaying = getString(R.string.link_youtube_trailer, items.key)
            openBrowser(urlPlaying)
        }
    }

    private fun openBrowser(url: String) {
        try {
            val isSecureConnection = url.contains("https://", false)
            val isDomainWWW = url.contains("www.", false)

            val uri = when {
                isSecureConnection -> url
                isDomainWWW -> url.replace("www.", "https://www.")
                else -> "https://www.".plus(url)
            }
            val customTabsIntent = CustomTabsIntent.Builder().apply {
                setToolbarColor(getColorCompat(R.color.colorPrimary))
                setSecondaryToolbarColor(getColorCompat(R.color.colorPrimaryDark))
            }.build()
            customTabsIntent.launchUrl(this, Uri.parse(uri))
        } catch (e: ActivityNotFoundException) {
            e.cause?.message?.let { this.toast(it) }
        }
    }

    private fun addDataReview(data: List<ReviewList>) {
        resultReview.clear()
        resultReview.addAll(data)
        adapterReview.notifyDataSetChanged()
    }

    override fun onClickTrailer(items: TrailerList) {

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
                setupContent(result)
            })
        })

        viewModel.reviewMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, _ ->
                addDataReview(result.results)
            })
        })
    }
}
