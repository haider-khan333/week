package com.android.weeknumber.service

import android.content.Intent
import android.widget.RemoteViewsService

class EventListService : RemoteViewsService() {
    override fun onGetViewFactory(p0: Intent?): RemoteViewsFactory {
        return EventListFactory(this.applicationContext)
    }
}