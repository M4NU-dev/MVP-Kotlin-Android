package com.example.mvpkotlinandroid.Helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SharedMemory {
    fun setValue(ctx: Context?, key: String, value: Boolean) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        val editor = prefs.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun setValue(ctx: Context?, key: String, value: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        prefs.edit().putString(key, value).apply()
    }

    fun setValue(ctx: Context?, key: String, value: Int) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        prefs.edit().putInt(key, value).apply()
    }

    fun getStringValue(ctx: Context?, key: String, value: String = ""): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        return prefs.getString(key, value)
    }

    fun getIntValue(ctx: Context?, key: String, value: Int = 0): Int {
        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        return prefs.getInt(key, value)
    }

    fun getBoolean(ctx: Context?, key: String, defaultValue: Boolean = false): Boolean {
        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        return prefs.getBoolean(key, defaultValue)
    }
}