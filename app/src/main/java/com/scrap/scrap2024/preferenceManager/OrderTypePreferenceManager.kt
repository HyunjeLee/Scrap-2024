package com.scrap.scrap2024.preferenceManager

import android.content.Context
import com.scrap.scrap2024.data.enums.OrderType

object OrderTypePreferenceManager : PreferenceManager<OrderType> {

    override val KEY_TYPE: String = "order_type"

    override fun getInitialType(context: Context): OrderType {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val orderType = sharedPref.getString(KEY_TYPE, null)    // default null

        return if (orderType != null) { // 앱 초기 실행이 아닌 경우
            if (orderType == OrderType.ASC.name) OrderType.ASC
            else OrderType.DESC
        } else {    // null // 초기 실행
            OrderType.ASC // default
        }
    }

}