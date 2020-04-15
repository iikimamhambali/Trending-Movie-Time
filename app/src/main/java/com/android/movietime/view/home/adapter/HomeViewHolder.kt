package com.android.movietime.view.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.movietime.R
import com.android.movietime.data.entity.DiscoverMovieList
import com.android.movietime.extention.loadFromUrlWithPlaceholder
import kotlinx.android.synthetic.main.layout_item_video.view.*

class HomeViewHolder(view: View, private val listener: SetOnClickVideo) :
    RecyclerView.ViewHolder(view) {

    fun bind(items: DiscoverMovieList) {
        with(itemView) {
            val image = "https://image.tmdb.org/t/p/w500" + items.posterPath
            ivThumbnailMovie.loadFromUrlWithPlaceholder(
                image,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background
            )
            tvTitle.text = items.title
            tvReleaseDate.text = items.releaseDate
            tvDescription.text = items.overview

            cvVideo.setOnClickListener { listener.onClickVideo(items) }
        }
    }

    interface SetOnClickVideo {

        fun onClickVideo(items: DiscoverMovieList)
    }

}