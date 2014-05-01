package com.shaap.angelteichanlage.de;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.JsonReader;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
            database.delete("News","",null);
            while(reader.hasNext()) {
                reader.beginObject();
                ContentValues cv = new ContentValues();
                cv.put("read",0);
                while(reader.hasNext()) {
                    String name = reader.nextName();
                    if (name.equals("title")) {
                        cv.put("title",reader.nextString());
                    } else if (name.equals("description")) {
                            cv.put("description",reader.nextString());
                    } else {
                        reader.skipValue();
                    }
                }
                database.insert("News", null, cv);
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
