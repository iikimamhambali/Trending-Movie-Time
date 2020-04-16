package com.android.movietime.view.home.adapter

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.movietime.R
import com.android.movietime.data.entity.GenreList
import com.android.movietime.extention.getDrawableCompat
import kotlinx.android.synthetic.main.layout_item_genre.view.*
import org.jetbrains.anko.textColor

class GenreViewHolder(view: View, private val listener: SetOnClickGenre) :
    RecyclerView.ViewHolder(view) {

    fun bind(items: GenreList) {
        with(itemView) {
            tvGenre.text = items.name
            sectionGenre.setOnClickListener {
                listener.onClickGenre(items)
                listener.setBackgroundContent(items, adapterPosition)
            }
        }
    }

    private fun setupBackgroundContent(items: GenreList) {
        with(itemView) {
            when (items.isCheck) {
                true -> {
                    sectionGenre.background =
                        context.getDrawableCompat(R.drawable.bg_container_color)
                    tvGenre.textColor = Color.WHITE
                }
                else -> {
                    sectionGenre.background =
                        context.getDrawableCompat(R.drawable.bg_container_line)
                    tvGenre.textColor = Color.BLACK
                }
            }
        }
    }

    interface SetOnClickGenre {

        fun onClickGenre(items: GenreList)

        fun setBackgroundContent(items: GenreList, position: Int)
    }
}