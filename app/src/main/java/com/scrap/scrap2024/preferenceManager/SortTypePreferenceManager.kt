package com.scrap.scrap2024.preferenceManager

import android.content.Context
import com.scrap.scrap2024.data.enums.SortType

object SortTypePreferenceManager : PreferenceManager<SortType> {

    override val KEY_TYPE: String = "sort_type"

    override fun getInitialType(context: Context): SortType {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val sortType = sharedPref.getString(KEY_TYPE, null)    // default null

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
                    throw IllegalArgumentException(errorMessage)
                }
            }
        } else {    // null // 초기 실행
            SortType.SCRAP_DATE // default
        }
    }

}