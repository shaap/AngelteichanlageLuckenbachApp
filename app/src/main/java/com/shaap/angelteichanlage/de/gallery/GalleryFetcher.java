package com.shaap.angelteichanlage.de.gallery;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.util.JsonReader;

import com.shaap.angelteichanlage.de.JsonParser;
import com.shaap.angelteichanlage.de.db.DatabaseHelper;

import java.util.List;

import static android.os.StrictMode.setThreadPolicy;

/**
 * Created by Sebastian on 03.05.2014.
 */
public class GalleryFetcher {

    private static final String GALLERY_URL = "http://www.angelteichanlage.de/app/gallery.php";

    private DatabaseHelper dbhelp;
    private SQLiteDatabase database;
    public GalleryFetcher(Context context) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        setThreadPolicy(policy);
        dbhelp = new DatabaseHelper(context);
        database = dbhelp.getWritableDatabase();
    }
    public boolean Fetch() {

        try {
            database.beginTransaction();
            database.delete("Gallery",null,null);
            JsonReader reader = JsonParser.getJSONFromUrl(GALLERY_URL);
            reader.beginArray();
            while(reader.hasNext()) {
                reader.beginObject();
                ContentValues cv = new ContentValues();
                while(reader.hasNext()) {
                    String name = reader.nextName();
                    if (name.equals("src_big")) {
                        cv.put("url",reader.nextString());
                    } else if (name.equals("created")) {
                        cv.put("timestamp", reader.nextString());
                    } else if (name.equals("caption")) {
                        cv.put("caption",reader.nextString());
                    } else {
                        reader.skipValue();
                    }
                }
                database.insert ("Gallery", null, cv);
                reader.endObject();
            }
            reader.endArray();
            reader.close();
            database.setTransactionSuccessful();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            database.endTransaction();
        }

    }
}
