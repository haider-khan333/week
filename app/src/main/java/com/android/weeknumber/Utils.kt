package com.android.weeknumber

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.CalendarContract
import java.util.Calendar

class Utils {
    private val motivationsForMen = listOf(
        "🔥 Legacy – Build something that outlives you.",
        "🎯 Discipline Over Motivation – Winners don’t rely on motivation; they rely on habits.",
        "💪 Physical Strength – A strong body leads to a strong mind. Train daily.",
        "🧠 Mental Resilience – Life is tough, but you are tougher.",
        "💰 Financial Freedom – Money buys time, security, and choices.",
        "🏋️ Self-Reliance – Be the man who doesn’t need rescuing.",
        "⚔️ Respect Over Attention – Seek to be respected, not just noticed.",
        "📚 Growth Mindset – Always be learning, always be evolving.",
        "🦾 Stoicism – Control what you can, endure what you must.",
        "⏳ Time is Limited – Every day wasted is a day you don’t get back.",
        "👨‍👩‍👧‍👦 Protect & Provide – Be the rock for your family and community.",
        "🏆 Mastery – Be exceptional at something; don’t settle for mediocrity.",
        "🏗️ Hard Work Wins – Grind now, enjoy the rewards later.",
        "🚫 No Excuses – Take ownership of everything in your life.",
        "🛡️ Respect Your Name – Build a reputation that your future self will thank you for.",
        "🤝 Brotherhood – Surround yourself with strong, driven men who push you higher.",
        "🦅 Attract, Don’t Chase – Become so valuable that success, women, and opportunities come to you.",
        "😈 Face Your Fears – Confidence is built by conquering what scares you.",
        "👑 Be a Leader – Inspire others through your actions, not just words.",
        "🎢 Enjoy the Process – The journey makes the man, not just the destination.",
        "🦍 Strength is Earned – Nothing great comes easy. Keep pushing.",
        "🌎 Conquer Yourself – Before you change the world, master your mind.",
        "🔑 Self-Control is Power – The man who controls himself controls everything.",
        "🏹 Focus Like a Hunter – Eliminate distractions and hit your targets.",
        "🎭 No One Cares – Work harder, prove yourself through results, not words.",
        "💼 Build Your Empire – Work like a king, think like a warrior.",
        "⏰ Your Time is Now – Stop waiting. Take action today.",
        "🦁 Dominate Every Arena – Wherever you go, make an impact.",
        "💎 Be Valuable – Instead of chasing opportunities, become so skilled that they chase you.",
        "🚀 Work in Silence – Let your success make the noise.",
        "🔥 Burn the Excuses – No more complaining. Get up and make things happen.",
        "💡 Learn, Adapt, Dominate – The strongest men evolve with challenges.",
        "🌊 Stay Unshaken – Be like the ocean—calm but powerful when needed.",
        "🚴‍♂️ No Comfort Zone – Growth happens when you're uncomfortable.",
        "🏋️‍♂️ Train Your Body & Mind – A weak man is a liability to himself.",
        "💀 Face Your Demons – The things you fear hold the keys to your next level.",
        "🌱 Start Now, Improve Later – The best time to begin was yesterday; the second-best time is now.",
        "💥 Keep Moving Forward – Never let failure define you; let it refine you.",
        "🛠️ Build Your Own Path – Stop following. Carve your own way.",
        "🎖️ Be a Man of Honor – Live by principles, not opinions."
    )

    fun giveRandomMotivation(): String {
        return motivationsForMen.random()
    }

//    fun getCalendarEvents(contentResolver: ContentResolver): List<CalendarEvent> {
//        val events = mutableListOf<CalendarEvent>()
//
//        // Define the columns to retrieve
//        val projection = arrayOf(
//            CalendarContract.Events._ID,
//            CalendarContract.Events.TITLE,
//            CalendarContract.Events.DESCRIPTION,
//            CalendarContract.Events.DTSTART,
//            CalendarContract.Events.DTEND,
//            CalendarContract.Events.EVENT_LOCATION
//        )
//
//        // Define the date range for the query
//        val startOfDay = Calendar.getInstance().apply {
//            set(Calendar.HOUR_OF_DAY, 0)
//            set(Calendar.MINUTE, 0)
//            set(Calendar.SECOND, 0)
//            set(Calendar.MILLISECOND, 0)
//        }.timeInMillis
//
//        val endOfDay = Calendar.getInstance().apply {
//            set(Calendar.HOUR_OF_DAY, 23)
//            set(Calendar.MINUTE, 59)
//            set(Calendar.SECOND, 59)
//            set(Calendar.MILLISECOND, 999)
//        }.timeInMillis
//
//        // Define the selection criteria
//        val selection =
//            "${CalendarContract.Events.DTSTART} >= ? AND ${CalendarContract.Events.DTEND} <= ?"
//        val selectionArgs = arrayOf(startOfDay.toString(), endOfDay.toString())
//
//        // Query the calendar events
//        val cursor: Cursor? = contentResolver.query(
//            CalendarContract.Events.CONTENT_URI,
//            projection,
//            selection,
//            selectionArgs,
//            null
//        )
//
//        cursor?.use {
//            while (it.moveToNext()) {
//                val id = it.getLong(it.getColumnIndexOrThrow(CalendarContract.Events._ID))
//                val title = it.getString(it.getColumnIndexOrThrow(CalendarContract.Events.TITLE))
//                val description =
//                    it.getString(it.getColumnIndexOrThrow(CalendarContract.Events.DESCRIPTION))
//                val startTime =
//                    it.getLong(it.getColumnIndexOrThrow(CalendarContract.Events.DTSTART))
//                val endTime = it.getLong(it.getColumnIndexOrThrow(CalendarContract.Events.DTEND))
//                val location =
//                    it.getString(it.getColumnIndexOrThrow(CalendarContract.Events.EVENT_LOCATION))
//
//                events.add(CalendarEvent(id, title, description, startTime, endTime, location))
//            }
//        }
//
//        return events
//    }
//
//    data class CalendarEvent(
//        val id: Long,
//        val title: String,
//        val description: String,
//        val startTime: Long,
//        val endTime: Long,
//        val location: String
//    )

    fun getCalendarEvents(context: Context): Triple<List<CalendarEvent>, List<CalendarEvent>, List<CalendarEvent>> {
        val todayEvents = mutableListOf<CalendarEvent>()
        val tomorrowEvents = mutableListOf<CalendarEvent>()
        val upcomingWeekEvents = mutableListOf<CalendarEvent>()

        val calendar = Calendar.getInstance()

        // Get the start and end timestamps for today
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val todayStart = calendar.timeInMillis

        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        val todayEnd = calendar.timeInMillis

        // Get the start and end timestamps for tomorrow
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrowStart = calendar.timeInMillis

        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        val tomorrowEnd = calendar.timeInMillis

        // Get the timestamp for the upcoming week (7 days from now)
        calendar.add(Calendar.DAY_OF_YEAR, 6) // Move from tomorrow to 7 days later
        val weekEnd = calendar.timeInMillis

        // Define the URI to query calendar events
        val uri = CalendarContract.Events.CONTENT_URI

        // Specify the columns to retrieve
        val projection = arrayOf(
            CalendarContract.Events._ID,
            CalendarContract.Events.TITLE,
            CalendarContract.Events.DTSTART,
            CalendarContract.Events.DTEND
        )

        // Fetch events from today onwards
        val selection = "${CalendarContract.Events.DTSTART} >= ?"
        val selectionArgs = arrayOf(todayStart.toString())

        // Query the calendar provider
        val cursor: Cursor? = context.contentResolver.query(
            uri,
            projection,
            selection,
            selectionArgs,
            CalendarContract.Events.DTSTART + " ASC"
        )

        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getLong(0)
                val title = it.getString(1)?.trim() ?:"Untitled Event"
                val startDate = it.getLong(2)
                val endDate = it.getLong(3)

                when (startDate) {
                    in todayStart..todayEnd -> todayEvents.add(CalendarEvent(id, title, startDate, endDate))
                    in tomorrowStart..tomorrowEnd -> tomorrowEvents.add(CalendarEvent(id, title, startDate, endDate))
                    in tomorrowEnd..weekEnd -> upcomingWeekEvents.add(CalendarEvent(id, title, startDate, endDate))
                }
            }
        }

//        return Triple(todayEvents, tomorrowEvents, upcomingWeekEvents)
        return Triple(
            todayEvents.distinctBy { it.title to it.startTime },
            tomorrowEvents.distinctBy { it.title to it.startTime },
            upcomingWeekEvents.distinctBy { it.title to it.startTime }
        )
    }

    // Data class for storing event details
    data class CalendarEvent(
        val id: Long,
        val title: String,
        val startTime: Long,
        val endTime: Long
    )
}