package com.axionlabs.chimespace.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {
    private const val PREFS_NAME = "MyAppPrefs"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun putValue(
        key: String,
        value: Boolean,
    ) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getValue(
        key: String,
        value: Boolean,
    ): Boolean = sharedPreferences.getBoolean(key, value) ?: value

    fun putValue(
        key: String,
        value: String,
    ) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getValue(
        key: String,
        value: String,
    ): String = sharedPreferences.getString(key, value) ?: value
}
