package com.scrap.scrap2024.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        // 현재 아이템 위치
        val position = parent.getChildAdapterPosition(view)
        // 현재 아이템이 속한 열 // 0 or 1
        val column = position % spanCount

        if (includeEdge) {  // 가장자리 포함하여 간격 설정
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount
            outRect.bottom = spacing // 아이템 하단 간격

            if (position < spanCount) { // 첫 번째 행
                outRect.top = spacing
            }
        } else {    // 가장자리 미포함
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                outRect.top = spacing // 첫 번째 행
            }
        }
    }
}