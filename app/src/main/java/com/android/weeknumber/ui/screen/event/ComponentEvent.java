package com.android.weeknumber.ui.screen.event;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.android.weeknumber.R;
import com.android.weeknumber.Utils;

public class ComponentEvent extends LinearLayout {
    private TextView eventTime;
    private TextView eventTitle;
    private LinearLayout eventColor;

    public ComponentEvent(Context context) {
        super(context);
        init();
    }

    public ComponentEvent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentEvent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.event_component, this);
        eventTime = findViewById(R.id.eventTime);
        eventTitle = findViewById(R.id.eventTitle);
        eventColor = findViewById(R.id.eventColor);
    }

    public void changeLineColor(int color) {
        eventColor.setBackgroundColor(color);
    }

    public void updateEventDetails(Utils.CalendarEvent event) {


    }
}
