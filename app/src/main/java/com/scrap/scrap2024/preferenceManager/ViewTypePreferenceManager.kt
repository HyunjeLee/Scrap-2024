package com.scrap.scrap2024.preferenceManager

import android.content.Context
import com.scrap.scrap2024.data.ViewType

object ViewTypePreferenceManager : PreferenceManager<ViewType> {

    override val KEY_TYPE: String = "view_type"

    override fun getInitialType(context: Context): ViewType {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val viewType = sharedPref.getString(KEY_TYPE, null)    // string? 으로 반환

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
                    throw IllegalArgumentException(errorMessage)
                }
            }
        } else {    //null // 초기 실행
            ViewType.LIST // default
        }
    }

}