package com.android.weeknumber.ui.screen.widget

import android.content.Context
import android.content.SharedPreferences

object WidgetPreferences {
    private const val PREFS_NAME = "WeekNumberPrefs"
    private const val LAST_WEEK_KEY = "last_week"

    fun getLastUpdatedWeek(context: Context): Int {
        val prefs: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(LAST_WEEK_KEY, -1) // Default: -1 (forces update on first run)
    }

    fun setLastUpdatedWeek(context: Context, weekNumber: Int) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(LAST_WEEK_KEY, weekNumber).apply()
    }
}