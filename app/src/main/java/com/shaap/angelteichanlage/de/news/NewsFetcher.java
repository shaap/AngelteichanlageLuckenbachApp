package com.shaap.angelteichanlage.de.news;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.JsonReader;

import com.shaap.angelteichanlage.de.JsonParser;
import com.shaap.angelteichanlage.de.db.DatabaseHelper;

import static android.os.StrictMode.*;

/**
 * Created by shaap on 30.04.14.
 */
public class NewsFetcher {

    private static final String NEWS_URL = "http://www.angelteichanlage.de/app/newsticker.php";

    private DatabaseHelper dbhelp;
    private SQLiteDatabase database;

    public NewsFetcher(Context context) {
        ThreadPolicy policy = new ThreadPolicy.Builder().permitAll().build();
        setThreadPolicy(policy);
        dbhelp = new DatabaseHelper(context);
        database = dbhelp.getWritableDatabase();
    }
    public boolean FetchNews() {

        try {
            database.beginTransaction();
            JsonReader reader = JsonParser.getJSONFromUrl(NEWS_URL);
            reader.beginArray();
            while(reader.hasNext()) {
                reader.beginObject();
                ContentValues cv = new ContentValues();
                cv.put("read",0);
                while(reader.hasNext()) {
                    String name = reader.nextName();
                    if (name.equals("title")) {
                        cv.put("title",reader.nextString());
                    } else if (name.equals("description")) {
                            cv.put("description", reader.nextString());
                    } else if (name.equals("id")) {
                            cv.put("id",reader.nextString());
                    } else {
                        reader.skipValue();
                    }
                }
                database.insertWithOnConflict("News", null, cv, SQLiteDatabase.CONFLICT_IGNORE);
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

