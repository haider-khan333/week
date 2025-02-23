package com.android.weeknumber.ui.screen.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import com.android.weeknumber.MainActivity
import com.android.weeknumber.R
import com.android.weeknumber.Utils
import com.android.weeknumber.ui.screen.weeknumber.WeekNumberUtils

class WeekNumberWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        Log.d("TAG", "onUpdate: Updating the scheduler")
        for (widgetId in appWidgetIds) {
            Log.d("TAG", "onUpdate: updating on upDate")
            updateAppWidget(context, appWidgetManager, widgetId)
        }


        WidgetUpdateScheduler.scheduleWidgetUpdate(context = context)

    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE || intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val componentName = ComponentName(context, WeekNumberWidgetProvider::class.java)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(componentName)

            for (widgetId in appWidgetIds) {
                Log.d("TAG", "onReceive: updating on onReceieve")
                updateAppWidget(context, appWidgetManager, widgetId)
            }

            WidgetUpdateScheduler.scheduleWidgetUpdate(context)

        }


    }

    companion object {
        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, widgetId: Int) {
            val remoteViews = RemoteViews(context.packageName, R.layout.widget_week_number)


            // Get current week number
            val weekNumberUtils = WeekNumberUtils()
            val util = Utils()
            val weekNumber = weekNumberUtils.getCurrentWeekNumber()

            val currentDay = weekNumberUtils.getCurrentDay()
            val monthDate = weekNumberUtils.getCurrentMonthDate()
            val time = weekNumberUtils.getCurrentTime()


            if (time.contains("12") && time.contains("am")) {
                val motivation = util.giveRandomMotivation()
                remoteViews.setTextViewText(R.id.motivationOfTheDay, motivation)
            }

            remoteViews.setTextViewText(R.id.timeOfWeek, time)
            remoteViews.setTextViewText(R.id.dateOfWeek, monthDate)
            remoteViews.setTextViewText(R.id.dayOfWeek, currentDay)
            remoteViews.setTextViewText(R.id.widgetWeekNumber, weekNumber.toString())


            // Open the app when the widget is clicked
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            remoteViews.setOnClickPendingIntent(R.id.widget_container, pendingIntent)

            // Update widget
            appWidgetManager.updateAppWidget(widgetId, remoteViews)
        }
    }


}