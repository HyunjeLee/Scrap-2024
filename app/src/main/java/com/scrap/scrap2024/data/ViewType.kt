package com.scrap.scrap2024.data

import android.content.Context
import android.util.Log

enum class ViewType {
    LIST, GRID
}

object ViewTypeManager {
    private const val PREF_NAME = "view_type_pref"
    private const val KEY_VIEW_TYPE = "view_type"

    fun initalViewType(context: Context): ViewType {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val viewType = sharedPref.getString(KEY_VIEW_TYPE, null)    // string? 으로 반환

        return if (viewType != null) { // 앱 초기 실행이 아닌 경우
            when (viewType) {
                ViewType.LIST.name -> {
                    ViewType.LIST
                }

                ViewType.GRID.name -> {
                    ViewType.GRID
                }

                else -> {   // error
                    val errorMessage = "Invalid viewType: $viewType"
                    Log.e("SHARED_PREF_VIEW_TYPE", errorMessage)
                    throw IllegalArgumentException(errorMessage)
                }
            }
        } else {    //null // 초기 실행
            ViewType.LIST // default
        }
    }

    fun saveViewType(context: Context, viewType: ViewType) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(KEY_VIEW_TYPE, viewType.name) // string으로 전달
            apply()
        }
    }

}