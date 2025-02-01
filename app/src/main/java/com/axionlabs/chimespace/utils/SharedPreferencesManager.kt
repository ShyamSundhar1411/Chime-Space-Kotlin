package com.axionlabs.chimespace.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {
    private const val PREFS_NAME = "MyAppPrefs"
    private lateinit var sharedPreferences: SharedPreferences
    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
    }
    fun saveBoolean(key: String, value: Boolean){
        sharedPreferences.edit().putBoolean(key,value).apply()
    }
    fun getBoolean(key: String, defaultValue: Boolean): Boolean{
        return sharedPreferences.getBoolean(key,defaultValue) ?: defaultValue
    }

}