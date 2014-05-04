package com.shaap.angelteichanlage.de.gallery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Gallery;

import com.shaap.angelteichanlage.de.db.DatabaseHelper;
import com.shaap.angelteichanlage.de.news.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian on 03.05.2014.
 */
public class GalleryDatabase extends DatabaseHelper {
    public GalleryDatabase(Context context) {
        super(context);
    }
    /**
     * GALLERY Table
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Gallery Item
    void addItem(GalleryItem data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", data.getId()); // Gallery ID
        values.put("url", data.getURL()); // Image URL
        values.put("timestamp", data.getTimestamp()); // Timestamp
        values.put("caption", data.getText()); // Text to display below photo

        // Inserting Row
        db.insert("Gallery", null, values);
        db.close(); // Closing database connection
    }

    // Getting single Gallery Item
    public GalleryItem getItem (int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("Gallery", new String[] { "id",
                        "url", "timestamp", "caption"}, "id" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        GalleryItem data = new GalleryItem(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)),cursor.getString(3));
        // return contact
        return data;
    }


    // Getting All Gallery Items
    public List<GalleryItem> getAll() {
        List<GalleryItem> dataList = new ArrayList<GalleryItem>();
        // Select All Query
        String selectQuery = "SELECT  id,url,timestamp,caption FROM " + "Gallery";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GalleryItem data = new GalleryItem();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setURL(cursor.getString(1));
                data.setTimestamp(Integer.parseInt(cursor.getString(2)));
                data.setText(cursor.getString(3));
                // Adding Gallery Item to list
                dataList.add(data);
            } while (cursor.moveToNext());
        }

        // return Gallery list
        return dataList;
    }

    // Deleting single Gallery Item
    public void deleteItem(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        String id = String.valueOf(position);
        db.delete("Gallery", "id" + "="+id,null);
        //Updating table
    }


    // Getting Gallery Count
    public int getCount() {
        String countQuery = "SELECT  id FROM " + "Gallery";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        // return count
        return cursor.getCount();
    }
}
