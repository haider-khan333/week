package com.android.weeknumber.ui.screen.weeknumber

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class WeekNumberUtils {
    fun getCurrentWeekNumber(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.WEEK_OF_YEAR)
    }

    fun getCurrentTime(): String {
        val sdf = SimpleDateFormat("h:mm a", Locale.getDefault()) // Format: 7:51 PM/AM
        return sdf.format(Date())
    }

    fun getCurrentMonthDate(): String {
        val sdf = SimpleDateFormat("MMM d", Locale.getDefault()) // Format: Jun 10
        return sdf.format(Date())
    }

    fun getCurrentDay(): String {
        val sdf = SimpleDateFormat("EEEE", Locale.getDefault()) // Format: Wednesday
        return sdf.format(Date())
    }




}