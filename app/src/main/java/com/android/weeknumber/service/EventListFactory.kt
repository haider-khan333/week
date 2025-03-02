package com.android.weeknumber.service

import android.content.Context
import android.view.View
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.compose.ui.graphics.toArgb
import com.android.weeknumber.R
import com.android.weeknumber.Utils
import com.android.weeknumber.ui.theme.E1
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EventListFactory(private val context: Context) : RemoteViewsService.RemoteViewsFactory {

    private var eventList: Triple<List<Utils.CalendarEvent>, List<Utils.CalendarEvent>, List<Utils.CalendarEvent>> =
        Triple(emptyList(), emptyList(), emptyList())
    private var utils = Utils()

    override fun onCreate() {}

    override fun onDataSetChanged() {
        eventList = utils.getCalendarEvents(context) // Fetch updated events
    }

    override fun onDestroy() {}

    override fun getCount(): Int {
        return if (eventList.first.isEmpty()) 1 else eventList.first.size
    }

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(context.packageName, R.layout.event_component)


        if (eventList.first.isNotEmpty()) {
            val event = eventList.first[position]
            rv.setTextViewText(R.id.eventTime, formatEventTime(event.startTime, event.endTime))
            rv.setTextViewText(R.id.eventTitle, event.title)

            // Show event details
            rv.setViewVisibility(R.id.eventTime, View.VISIBLE)
            rv.setViewVisibility(R.id.eventTitle, View.VISIBLE)
            rv.setViewVisibility(R.id.noEventsText, View.GONE) // Hide "No Events Found"

            // set the event color
            event.color?.let {
                rv.setInt(R.id.eventColor, "setBackgroundColor", it)
            }


        } else {
            rv.setViewVisibility(R.id.eventTime, View.GONE)
            rv.setViewVisibility(R.id.eventTitle, View.GONE)
            rv.setViewVisibility(R.id.noEventsText, View.VISIBLE)

            // set the event color
            rv.setInt(R.id.eventColor, "setBackgroundColor", E1.toArgb())

        }

        return rv
    }

    private fun formatEventTime(startTime: Long, endTime: Long): String {
        val formatter =
            SimpleDateFormat("hh:mm a", Locale.getDefault()) // 12-hour format with AM/PM
        val startFormatted = formatter.format(Date(startTime))
        val endFormatted = formatter.format(Date(endTime))
        return "$startFormatted - $endFormatted"
    }


    override fun getLoadingView(): RemoteViews? = null
    override fun getViewTypeCount(): Int = 1
    override fun getItemId(position: Int): Long = position.toLong()
    override fun hasStableIds(): Boolean = true
}