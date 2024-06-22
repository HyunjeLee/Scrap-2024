package com.scrap.scrap2024.data

import android.content.Context
import android.util.Log

enum class SortType {
    TITLE, SCRAP_DATE
}

object SortTypeManager {
    private const val PREF_NAME = "sort_type_pref"
    private const val KEY_SORT_TYPE = "sort_type"

    fun initalSortType(context: Context): SortType {
        val sharedPref =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val sortType = sharedPref.getString(KEY_SORT_TYPE, null)    // default null

        return if (sortType != null) { // 앱 초기 실행이 아닌 경우
            when (sortType) {
                SortType.SCRAP_DATE.name -> {
                    SortType.SCRAP_DATE
                }

                SortType.TITLE.name -> {
                    SortType.TITLE
                }

                else -> {   // error
                    val errorMessage = "Invalid sortType: $sortType"
                    Log.e("SHARED_PREF_SORT_TYPE", errorMessage)
                    throw IllegalArgumentException(errorMessage)
                }
            }
        } else {    // null // 초기 실행
            SortType.SCRAP_DATE // default
        }
    }

    fun saveSortType(context: Context, sortType: SortType) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(KEY_SORT_TYPE, sortType.name)
            apply()
        }
    }
}