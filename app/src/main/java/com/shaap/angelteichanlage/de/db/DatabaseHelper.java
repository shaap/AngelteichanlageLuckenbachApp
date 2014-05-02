package com.shaap.angelteichanlage.de.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;

import com.shaap.angelteichanlage.de.news.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaap on 30.04.14.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "main";

    private static final int DATABASE_VERSION = 2;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table News( id integer primary key,title text not null,description text not null,read integer not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS News");
        onCreate(database);
    }

    /**
     * NEWS Table
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new News Item
    void addNews(NewsItem data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", data.get_id()); // News ID
        values.put("title", data.getTitle()); // News Title
        values.put("description", data.getDescription()); // News Content
        values.put("read", data.getIsRead()); // Flag to show, if News was marked as read

        // Inserting Row
        db.insert("News", null, values);
        db.close(); // Closing database connection
    }

    // Getting single News Item
    NewsItem getNews(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("News", new String[] { "_id",
                "title", "description", "read"}, "_id" + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        NewsItem data = new NewsItem(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),Boolean.parseBoolean(cursor.getString(3)));
        // return contact
        return data;
    }


    // Getting All News
    public List<NewsItem> getAllNews() {
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
                data.setIsRead(Boolean.parseBoolean(cursor.getString(3)));
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

        String update = "UPDATE News SET read = '"+ (value == true ? 1 : 2) +"' WHERE ID = " + position;
        db.execSQL(update);

    }

    // Deleting single News Item
    public void deleteValues(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        String id = String.valueOf(position);
        db.delete("News", "_id" + "="+id,null);
        //Updating table
    }


    // Getting News Count
    public int getNewsCount() {
        String countQuery = "SELECT  * FROM " + "News";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        // return count
        return cursor.getCount();
    }
}