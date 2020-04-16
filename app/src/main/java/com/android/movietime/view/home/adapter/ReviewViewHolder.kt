package com.android.movietime.view.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.movietime.data.entity.ReviewList
import kotlinx.android.synthetic.main.layout_item_review.view.*

class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(items: ReviewList) {
        with(itemView) {
            tvAuthor.text = items.author
            tvContent.text = items.content
        }
    }

}