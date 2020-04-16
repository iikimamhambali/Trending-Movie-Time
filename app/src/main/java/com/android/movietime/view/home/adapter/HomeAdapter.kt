package com.android.movietime.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.movietime.R
import com.android.movietime.data.entity.DiscoverMovieList

class HomeAdapter(
    private val items: List<DiscoverMovieList>,
    private val listener: HomeViewHolder.SetOnClickVideo
) :
    RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_item_video,
                parent,
                false
            ), listener
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(items[position])
    }

}