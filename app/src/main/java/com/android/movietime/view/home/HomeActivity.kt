package com.android.movietime.view.home

import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import com.android.movietime.R
import com.android.movietime.base.BaseActivity
import com.android.movietime.base.BaseRecyclerView
import com.android.movietime.data.entity.DiscoverMovieList
import com.android.movietime.data.entity.DiscoverMovieRequest
import com.android.movietime.data.entity.GenreList
import com.android.movietime.extention.getColorCompat
import com.android.movietime.view.detail.DetailMovieActivity
import com.android.movietime.view.detail.DetailMovieActivity.Companion.MOVIE_ID
import com.android.movietime.view.home.adapter.GenreAdapter
import com.android.movietime.view.home.adapter.GenreViewHolder
import com.android.movietime.view.home.adapter.HomeAdapter
import com.android.movietime.view.home.adapter.HomeViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.sectionEmptyState
import kotlinx.android.synthetic.main.layout_empty_state.*
import org.jetbrains.anko.startActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity(), HomeViewHolder.SetOnClickVideo,
    GenreViewHolder.SetOnClickGenre {

    private val viewModel by viewModel<HomeViewModel>()

    private var resultList = mutableListOf<DiscoverMovieList>()
    private val adapterVideo by lazy { HomeAdapter(resultList, this) }

    private var resultGenre = mutableListOf<GenreList>()
    private val adapterGenre by lazy { GenreAdapter(resultGenre, this) }

    private var position = 0
    private var currentPage = 1
    private var totalPage = 0
    private var isLoading = false

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        setupReyclerView()
    }

    override fun initEvent() {
        super.initEvent()
        setScrollListener()
        onClickEmptyState()
        setupSwipeRefresh()
    }

    private fun setupReyclerView() {
        with(rvMain) {
            initRecyclerView(adapterVideo, BaseRecyclerView.LayoutManager.VERTICAL)
        }

        with(rvGenre) {
            initRecyclerView(adapterGenre, BaseRecyclerView.LayoutManager.HORIZONTAL)
        }
    }

    private fun setupSwipeRefresh() {
        with(swipeRefreshHome) {
            setOnRefreshListener {
                resetData()
                loadingData()
            }
            setColorSchemeColors(
                context.getColorCompat(R.color.colorPrimary),
                context.getColorCompat(R.color.colorPrimary),
                context.getColorCompat(R.color.colorPrimary)
            )
            val typedValue = android.util.TypedValue()
            context.theme?.resolveAttribute(R.attr.actionBarSize, typedValue, true)
            setProgressViewOffset(
                false,
                0,
                context.resources.getDimensionPixelSize(typedValue.resourceId)
            )
        }
    }

    private fun setScrollListener() {
        nestedHome.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
                if (v.getChildAt(v.childCount - 1) != null && currentPage < totalPage && !isLoading) {
                    if ((scrollY >= (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight)) &&
                        scrollY > oldScrollY
                    ) {
                        loadingData(currentPage)
                    }
                }
            })
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

    private fun setEmptyStateContent(visible: Boolean) {
        when (visible) {
            true -> {
                sectionContent.visibility = View.GONE
                sectionEmptyState.visibility = View.VISIBLE
            }
            else -> {
                sectionEmptyState.visibility = View.GONE
                sectionContent.visibility = View.VISIBLE
            }
        }
    }

    private fun addData(data: List<DiscoverMovieList>) {
        val positionStart = resultList.size + 1
        val itemCount = data.size
        resultList.addAll(data)
        adapterVideo.notifyItemRangeInserted(positionStart, itemCount)
    }

    private fun resetData() {
        currentPage = 1
        resultList.clear()
        adapterVideo.notifyDataSetChanged()
    }

    private fun addDataGenre(data: List<GenreList>) {
        resultGenre.clear()
        resultGenre.addAll(data)
        adapterGenre.notifyDataSetChanged()
    }

    private fun onClickEmptyState() {
        btnReload.setOnClickListener {
            loadingData()
        }
    }

    override fun onClickVideo(items: DiscoverMovieList) {
        startActivity<DetailMovieActivity>(MOVIE_ID to items.id)
    }

    override fun onClickGenre(items: GenreList) {
        resetData()
        loadingData(page = 1, genre = items.id.toString())
    }

    private fun loadingData(page: Int, genre: String = "") {
        viewModel.getDiscoverMovie(
            DiscoverMovieRequest(
                getString(R.string.api_key),
                page = page,
                genre = genre
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
        loadingData(1)
        viewModel.getGenreMovie(DiscoverMovieRequest(getString(R.string.api_key)))
    }

    override fun observeData() {
        super.observeData()
        viewModel.discoverMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, pagination ->
                setEmptyStateContent(false)
                addData(result.list)
                result.page = currentPage
                currentPage++
                totalPage = result.totalPages
            })
        })

        viewModel.genreMovie.observe(this, Observer {
            parseObserveData(it, resultSuccess = { result, _ ->
                addDataGenre(result.genres)
            })
        })
    }

    override fun startLoading() {
        isLoading = true
        setVisibilityContent(false)
    }

    override fun stopLoading() {
        isLoading = false
        setVisibilityContent(true)
        swipeRefreshHome.isRefreshing = false
    }

    override fun onInternetError() {
        setEmptyStateContent(true)
    }
}
