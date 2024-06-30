package com.scrap.scrap2024.preferenceManager

import android.content.Context

interface PreferenceManager<T : Enum<T>> {
    val PREF_NAME: String
        get() = this.toString()
    val KEY_TYPE: String


    fun getInitialType(context: Context): T
    fun saveType(context: Context, type: T) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            remove(KEY_TYPE)
            putString(KEY_TYPE, type.name)
            apply()
        }
    }
}