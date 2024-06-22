package com.scrap.scrap2024.data

import android.content.Context

enum class OrderType {
    ASC, DESC
}

object OrderTypeManager {
    private const val PREF_NAME = "order_type_pref"
    private const val KEY_ORDER_TYPE = "order_type"

    fun initalOrderType(context: Context): OrderType {
        val sharedPref =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val orderType = sharedPref.getString(KEY_ORDER_TYPE, null)    // default null

        return if (orderType != null) { // 앱 초기 실행이 아닌 경우
            if (orderType == OrderType.ASC.name) OrderType.ASC
            else OrderType.DESC
        } else {    // null // 초기 실행
            OrderType.ASC // default
        }
    }

    fun saveOrderType(context: Context, orderType: OrderType) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(KEY_ORDER_TYPE, orderType.name)
            apply()
        }
    }
}