package com.android.movietime.view.detail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.movietime.R
import com.android.movietime.data.entity.TrailerList
import com.android.movietime.extention.loadFromUrlWithPlaceholder
import kotlinx.android.synthetic.main.layout_item_trailer.view.*

class TrailerViewHolder(view: View, private val listener: SetOnClickTrailer) :
    RecyclerView.ViewHolder(view) {

    fun bind(items: TrailerList) {
        with(itemView) {
            val urlThumbnail = items.key
            ivTrailer.loadFromUrlWithPlaceholder(
                context.getString(R.string.link_youtube_image, urlThumbnail),
                R.drawable.ic_launcher_background, R.drawable.ic_launcher_background
            )
            ivTrailer.setOnClickListener { listener.onClickTrailer(items) }
        }
    }

    interface SetOnClickTrailer {

        fun onClickTrailer(items: TrailerList)
    }
}