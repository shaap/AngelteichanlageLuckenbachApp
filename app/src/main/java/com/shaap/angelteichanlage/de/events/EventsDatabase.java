package com.shaap.angelteichanlage.de.events;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shaap.angelteichanlage.de.db.DatabaseHelper;
import com.shaap.angelteichanlage.de.news.NewsItem;
import com.shaap.angelteichanlage.de.util.ISO8601DateParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Sebastian on 04.05.2014.
 */
public class EventsDatabase extends DatabaseHelper {
    public EventsDatabase(Context context) {
        super(context);
    }
    /**
     * EVENTS Table
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Events Item
    void addItem(EventsItem data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", data.getId()); // Event ID
        values.put("name", data.getName()); // Event Title
        values.put("description", data.getDescription()); // Event Description
        values.put("start", ISO8601DateParser.toString(data.getStart())); // Start Date/Time
        values.put("end", ISO8601DateParser.toString(data.getEnd())); // End Date/Time
        values.put("icon", data.getIcon()); // Event Icon
        // Inserting Row
        db.insert("Events", null, values);
        db.close(); // Closing database connection
    }

    // Getting single Events Item
    public EventsItem getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("Events", new String[] { "id",
                        "name", "description", "start", "end", "icon"}, "id" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        EventsItem data = new EventsItem(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), ISO8601DateParser.parse(cursor.getString(3)),ISO8601DateParser.parse(cursor.getString(4)),cursor.getString(4));
        // return event
        return data;
    }


    // Getting All Events
    public List<EventsItem> getAll() {
        List<EventsItem> dataList = new ArrayList<EventsItem>();
        // Select All Query
        String selectQuery = "SELECT  id,name,description,start,end,icon FROM Events";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                EventsItem data = new EventsItem(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2), ISO8601DateParser.parse(cursor.getString(3)),ISO8601DateParser.parse(cursor.getString(4)),cursor.getString(4));
                dataList.add(data);
            } while (cursor.moveToNext());
        }

        // return event list
        return dataList;
    }

    // Deleting single Events Item
    public void deleteItem(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        String id = String.valueOf(position);
        db.delete("Events", "id" + "="+id,null);
        //Updating table
    }


    // Getting Events Count
    public int getCount() {
        String countQuery = "SELECT  id FROM " + "Events";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        // return count
        return cursor.getCount();
    }
}
