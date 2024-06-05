package com.scrap.scrap2024.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.scrap.scrap2024.utils.Utils.dpToPx

class GridSpacingItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {

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
            outRect.left = dpToPx(context, 15)
            outRect.right = dpToPx(context, 7)
        } else {
            // 1,3,5 번째 아이템
            outRect.left = dpToPx(context, 7)
            outRect.right = dpToPx(context, 15)
        }
        outRect.top = dpToPx(context, 7)
        outRect.bottom = dpToPx(context, 8)
    }


}