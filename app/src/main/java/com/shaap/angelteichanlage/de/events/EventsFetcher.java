package com.shaap.angelteichanlage.de.events;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.util.JsonReader;

import com.shaap.angelteichanlage.de.JsonParser;
import com.shaap.angelteichanlage.de.db.DatabaseHelper;

import static android.os.StrictMode.setThreadPolicy;

/**
 * Created by Sebastian on 04.05.2014.
 */
public class EventsFetcher {
    private static final String EVENTS_URL = "http://www.angelteichanlage.de/app/events.php";

    private DatabaseHelper dbhelp;
    private SQLiteDatabase database;

    public EventsFetcher(Context context) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        setThreadPolicy(policy);
        dbhelp = new DatabaseHelper(context);
        database = dbhelp.getWritableDatabase();
    }
    public boolean Fetch() {

        try {
            database.beginTransaction();
            database.delete("Events",null,null);
            JsonReader reader = JsonParser.getJSONFromUrl(EVENTS_URL);
            reader.beginArray();
            while(reader.hasNext()) {
                reader.beginObject();
                ContentValues cv = new ContentValues();
                while(reader.hasNext()) {
                    String name = reader.nextName();
                    if (name.equals("name")) {
                        cv.put("name",reader.nextString());
                    } else if (name.equals("description")) {
                        cv.put("description", reader.nextString());
                    } else if (name.equals("id")) {
                        cv.put("id",reader.nextString());
                    } else if (name.equals("start")) {
                        cv.put("start",reader.nextString());
                    } else if (name.equals("end")) {
                        cv.put("end",reader.nextString());
                    } else if (name.equals("icon")) {
                        cv.put("icon",reader.nextString());
                    } else {
                        reader.skipValue();
                    }
                }
                database.insert("Events", null, cv);
                reader.endObject();
            }
            reader.endArray();
            reader.close();
            database.setTransactionSuccessful();
            database.endTransaction();
            return true;
        } catch (Exception ex) {
            return false;
        }

    }
}
