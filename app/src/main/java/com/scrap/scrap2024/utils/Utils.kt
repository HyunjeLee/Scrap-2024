package com.scrap.scrap2024.utils

import android.content.Context

object Utils {
    fun dpToPx(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }
}
