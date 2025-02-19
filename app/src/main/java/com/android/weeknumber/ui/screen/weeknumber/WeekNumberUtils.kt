package com.android.weeknumber.ui.screen.weeknumber

import java.util.Calendar

class WeekNumberUtils {
    fun getCurrentWeekNumber(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.WEEK_OF_YEAR)
    }


}