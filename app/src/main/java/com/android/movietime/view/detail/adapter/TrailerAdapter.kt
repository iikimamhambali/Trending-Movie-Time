package com.android.movietime.view.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.movietime.R
import com.android.movietime.data.entity.TrailerList

class TrailerAdapter(
    private val items: List<TrailerList>,
    private val listener: TrailerViewHolder.SetOnClickTrailer
) :
    RecyclerView.Adapter<TrailerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder =
        TrailerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_item_trailer,
                parent,
                false
            ), listener
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.bind(items[position])
    }

}