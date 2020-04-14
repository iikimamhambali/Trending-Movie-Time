package com.android.movietime.base

import android.graphics.Rect
import android.view.View

class GridItemDecoration(val span: Int, val space: Int) :
    androidx.recyclerview.widget.RecyclerView.ItemDecoration() {

    companion object {
        const val HALF_RESULT = 2
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: androidx.recyclerview.widget.RecyclerView,
        state: androidx.recyclerview.widget.RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val positionItem = parent.getChildAdapterPosition(view)
        val grid = parent.layoutManager as androidx.recyclerview.widget.GridLayoutManager
        positionItem.let { position ->
            val spanIndex = grid.spanSizeLookup.getSpanIndex(position, span)

            when {
                isFirstSpan(spanIndex) -> outRect.left = space
                isLastSpan(spanIndex) -> outRect.right = space
                else -> {
                    outRect.left = halfSpace()
                    outRect.right = halfSpace()
                }
            }
            outRect.top = halfSpace()
        }
    }

    private fun halfSpace(): Int {
        return space / HALF_RESULT
    }

    private fun isFirstSpan(spanIndex: Int): Boolean {
        return spanIndex + 1 == 1
    }

    private fun isLastSpan(spanIndex: Int): Boolean {
        return spanIndex + 1 == span
    }
}