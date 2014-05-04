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

    private static final int DATABASE_VERSION = 4;

    // Database creation sql statement

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table News( id integer primary key,title text not null,description text not null,read integer not null);");
        database.execSQL("create table Gallery( id integer primary key,url text not null, timestamp integer not null, caption text not null);");
    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS News");
        database.execSQL("DROP TABLE IF EXISTS Gallery");
        onCreate(database);
    }


}