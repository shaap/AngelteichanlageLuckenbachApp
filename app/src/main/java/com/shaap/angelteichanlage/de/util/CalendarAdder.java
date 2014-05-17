package com.shaap.angelteichanlage.de.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.text.Html;

import com.shaap.angelteichanlage.de.MainActivity;
import com.shaap.angelteichanlage.de.events.EventsItem;

import java.util.Calendar;

/**
 * Created by Sebastian on 06.05.2014.
 */
public class CalendarAdder {
    public static void AddToCalendar(Context context, EventsItem event) {
        Calendar cal = Calendar.getInstance();

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        cal.setTime(event.getStart());
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.getTimeInMillis());
        intent.putExtra(Events.ALL_DAY, false);
        cal.setTime(event.getEnd());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.getTimeInMillis());
        intent.putExtra(Events.TITLE, event.getName());
        intent.putExtra(Events.DESCRIPTION, Html.fromHtml(event.getDescription()));
        intent.putExtra(Events.EVENT_LOCATION,"Angelteichanlage Luckenbach");
        context.startActivity(intent);
    }
}
