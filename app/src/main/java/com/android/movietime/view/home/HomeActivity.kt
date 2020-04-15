package com.android.movietime.view.home

import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.lifecycle.Observer
import com.android.movietime.R
import com.android.movietime.base.BaseActivity
import com.android.movietime.base.BaseRecyclerView
import com.android.movietime.data.entity.DiscoverMovieList
import com.android.movietime.data.entity.DiscoverMovieRequest
import com.android.movietime.data.entity.GenreList
import com.android.movietime.extention.getDrawableCompat
import com.android.movietime.view.detail.DetailMovieActivity
import com.android.movietime.view.detail.DetailMovieActivity.Companion.MOVIE_ID
import com.android.movietime.view.home.adapter.GenreAdapter
import com.android.movietime.view.home.adapter.GenreViewHolder
import com.android.movietime.view.home.adapter.HomeAdapter
import com.android.movietime.view.home.adapter.HomeViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_item_genre.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity(), HomeViewHolder.SetOnClickVideo,
    GenreViewHolder.SetOnClickGenre {

    private val viewModel by viewModel<HomeViewModel>()

    private var resultList = mutableListOf<DiscoverMovieList>()
    private val adapterVideo by lazy { HomeAdapter(resultList, this) }

    private var resultGenre = mutableListOf<GenreList>()
    private val adapterGenre by lazy { GenreAdapter(resultGenre, this) }

    private var position = 0

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        setupReyclerView()
    }

    private fun setupReyclerView() {
        with(rvMain) {
            initRecyclerView(adapterVideo, BaseRecyclerView.LayoutManager.VERTICAL)
        }

        with(rvGenre) {
            initRecyclerView(adapterGenre, BaseRecyclerView.LayoutManager.HORIZONTAL)
        }
    }

    private fun setVisibilityContent(visible: Boolean) {
        when (visible) {
            true -> {
                progressView.visibility = View.GONE
                sectionContent.visibility = View.VISIBLE
            }
            else -> {
                sectionContent.visibility = View.GONE
                progressView.visibility = View.VISIBLE
            }
        }
    }

    private fun addData(data: List<DiscoverMovieList>) {
        resultList.clear()
        resultList.addAll(data)
        adapterVideo.notifyDataSetChanged()
    }

    private fun addDataGenre(data: List<GenreList>) {
        resultGenre.clear()
        resultGenre.addAll(data)
        adapterGenre.notifyDataSetChanged()
    }

    override fun onClickVideo(items: DiscoverMovieList) {
        startActivity<DetailMovieActivity>(MOVIE_ID to items.id)
    }

    override fun onClickGenre(items: GenreList) {
        viewModel.getDiscoverMovie(
            DiscoverMovieRequest(
                getString(R.string.api_key),
                page = 1,
                genre = items.id.toString()
            )
        )
    }

    override fun setBackgroundContent(items: GenreList, position: Int) {
        this.position = position
        tvGenreTitle.text = resultGenre[position].name
        resultGenre[position].isCheck = true
    }

    override fun loadingData(isFromSwipe: Boolean) {
        super.loadingData(isFromSwipe)
        viewModel.getDiscoverMovie(DiscoverMovieRequest(getString(R.string.api_key), 1))

        viewModel.getGenreMovie(DiscoverMovieRequest(getString(R.string.api_key)))
    }

    override fun observeData() {
        super.observeData()
        viewModel.discoverMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, _ ->
                addData(result.list)
            })
        })

        viewModel.genreMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, _ ->
                addDataGenre(result.genres)
            })
        })
    }

    override fun startLoading() {
        setVisibilityContent(false)
    }

    override fun stopLoading() {
        setVisibilityContent(true)
    }
}
