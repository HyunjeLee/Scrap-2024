package com.scrap.scrap2024.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {

    private fun dpToPx(dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val spanCount = 2

        if (position % spanCount == 0) {
            // 0,2,4 번째 아이템
            outRect.left = dpToPx(15)
            outRect.right = dpToPx(7)
        } else {
            // 1,3,5 번째 아이템
            outRect.left = dpToPx(7)
            outRect.right = dpToPx(15)
        }
        outRect.top = dpToPx(7)
        outRect.bottom = dpToPx(8)
    }


}