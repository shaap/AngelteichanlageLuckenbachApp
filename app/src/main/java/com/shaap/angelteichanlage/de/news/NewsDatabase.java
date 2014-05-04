package com.shaap.angelteichanlage.de.news;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shaap.angelteichanlage.de.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian on 03.05.2014.
 */
public class NewsDatabase extends DatabaseHelper {
    public NewsDatabase(Context context) {
        super(context);
    }
    /**
     * NEWS Table
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new News Item
    void addItem(NewsItem data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", data.get_id()); // News ID
        values.put("title", data.getTitle()); // News Title
        values.put("description", data.getDescription()); // News Content
        values.put("read", data.getIsRead() ? 1 : 0); // Flag to show, if News was marked as read

        // Inserting Row
        db.insert("News", null, values);
        db.close(); // Closing database connection
    }

    // Getting single News Item
    public NewsItem getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("News", new String[] { "id",
                        "title", "description", "read"}, "id" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        NewsItem data = new NewsItem(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),Integer.parseInt(cursor.getString(3)) == 1 ? true : false);
        // return contact
        return data;
    }


    // Getting All News
    public List<NewsItem> getAll() {
        List<NewsItem> dataList = new ArrayList<NewsItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + "News";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NewsItem data = new NewsItem();
                data.set_id(Integer.parseInt(cursor.getString(0)));
                data.setTitle(cursor.getString(1));
                data.setDescription(cursor.getString(2));
                data.setIsRead(Integer.parseInt(cursor.getString(3)) == 1 ? true : false);
                // Adding News Item to list
                dataList.add(data);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataList;
    }

    // Updating News read
    public void setNewsRead(int position, Boolean value) {
        SQLiteDatabase db = this.getWritableDatabase();

        String update = "UPDATE News SET read = '"+ (value  ? 1 : 0) +"' WHERE ID = " + position;
        db.execSQL(update);

    }

    // Deleting single News Item
    public void deleteItem(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        String id = String.valueOf(position);
        db.delete("News", "id" + "="+id,null);
        //Updating table
    }


    // Getting News Count
    public int getCount() {
        String countQuery = "SELECT  id FROM " + "News";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        // return count
        return cursor.getCount();
    }
}
