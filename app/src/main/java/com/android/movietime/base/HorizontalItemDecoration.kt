package com.android.movietime.base

import android.graphics.Rect
import android.view.View

class HorizontalItemDecoration(val space: Int) :
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
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        when {
            isSingleItem(itemCount) -> {
                outRect.left = space
                outRect.right = space
            }
            isDoubeItem(itemCount) -> {
                when {
                    isFirstSpan(position) -> setLeftRight(outRect, space, halfSpace())
                    isLastSpan(position, itemCount) -> setLeftRight(outRect, halfSpace(), space)
                }
            }
            isFirstSpan(position) -> outRect.left = space
            isLastSpan(position, itemCount ?: 0) -> outRect.right = space
            else -> {
                outRect.left = halfSpace()
                outRect.right = halfSpace()
            }
        }
    }

    private fun setLeftRight(outRect: Rect, left: Int, right: Int) {
        outRect.left = left
        outRect.right = right
    }

    private fun halfSpace(): Int {
        return space / HALF_RESULT
    }

    private fun isSingleItem(dataSize: Int): Boolean {
        return dataSize == 1
    }

    private fun isDoubeItem(dataSize: Int): Boolean {
        return dataSize == 2
    }

    private fun isFirstSpan(index: Int): Boolean {
        return index + 1 == 1
    }

    private fun isLastSpan(index: Int, dataSize: Int): Boolean {
        return index + 1 == dataSize
    }
}