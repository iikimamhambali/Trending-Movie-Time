package com.android.movietime.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.movietime.R
import com.android.movietime.data.entity.GenreList

class GenreAdapter(
    private val items: List<GenreList>,
    private val listener: GenreViewHolder.SetOnClickGenre
) : RecyclerView.Adapter<GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder =
        GenreViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_item_genre,
                parent,
                false
            ), listener
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(items[position])
    }
}