package com.android.weeknumber.ui.screen.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import android.widget.RemoteViews.RemoteCollectionItems
import com.android.weeknumber.MainActivity
import com.android.weeknumber.R
import com.android.weeknumber.Utils
import com.android.weeknumber.service.EventListService
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
        if (intent.action == ACTION_REFRESH) {
            Log.d("TAG", "Refresh button clicked, updating widget...")

            val appWidgetManager = AppWidgetManager.getInstance(context)
            val componentName = ComponentName(context, WeekNumberWidgetProvider::class.java)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(componentName)

            for (widgetId in appWidgetIds) {
                Log.d("TAG", "Refreshing widget ID: $widgetId")
                updateAppWidget(context, appWidgetManager, widgetId)
            }

            // 🔄 Refresh the ListView data
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_event_list)
        }


    }

    companion object {
        private const val ACTION_REFRESH = "com.android.weeknumber.ACTION_REFRESH_WIDGET"

        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, widgetId: Int) {
            val remoteViews = RemoteViews(context.packageName, R.layout.widget_week_number)


            // Get current week number
            val weekNumberUtils = WeekNumberUtils()
            val util = Utils()
            val eventList = util.getCalendarEvents(context = context)
            val hasEvents = eventList.first.isNotEmpty()
            val weekNumber = weekNumberUtils.getCurrentWeekNumber()

            val currentDay = weekNumberUtils.getCurrentDay()
            val monthDate = weekNumberUtils.getCurrentMonthDate()
            val time = weekNumberUtils.getCurrentTime()


            // set the visibility of the text nad refresh button
            remoteViews.setViewVisibility(
                R.id.eventDetails,
                if (hasEvents) View.VISIBLE else View.GONE
            )

            // show/hide the list view
            remoteViews.setViewVisibility(
                R.id.widget_event_list,
                if (hasEvents) View.VISIBLE else View.GONE
            )

            // show/hide the "No events today" text
            remoteViews.setViewVisibility(
                R.id.noEventsText,
                if (hasEvents) View.GONE else View.VISIBLE
            )

//            remoteViews.setTextViewText(R.id.timeOfWeek, time)
            remoteViews.setTextViewText(R.id.dateOfWeek, monthDate)
            remoteViews.setTextViewText(R.id.dayOfWeek, currentDay)
            remoteViews.setTextViewText(R.id.widgetWeekNumber, weekNumber.toString())


            // Set up ListView to display dynamic events
            if (hasEvents) {
                val intent = Intent(context, EventListService::class.java)
                remoteViews.setRemoteAdapter(R.id.widget_event_list, intent)
                remoteViews.setEmptyView(R.id.widget_event_list, R.id.noEventsText)
            }

            // 🔄 Set up Refresh Button Click
            val refreshIntent = Intent(context, WeekNumberWidgetProvider::class.java).apply {
                action = ACTION_REFRESH
            }
            val refreshPendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                refreshIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            remoteViews.setOnClickPendingIntent(R.id.refreshIcon, refreshPendingIntent)


            // Open the app when the widget is clicked
            val openIntent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                openIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            remoteViews.setOnClickPendingIntent(R.id.week_weather_container, pendingIntent)

            // Update widget
            appWidgetManager.updateAppWidget(widgetId, remoteViews)
        }
    }


}