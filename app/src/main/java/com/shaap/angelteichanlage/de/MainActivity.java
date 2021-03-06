package com.shaap.angelteichanlage.de;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.*;
import android.widget.*;

import com.shaap.angelteichanlage.de.events.EventsActivity;
import com.shaap.angelteichanlage.de.events.EventsFetcher;
import com.shaap.angelteichanlage.de.gallery.GalleryActivity;
import com.shaap.angelteichanlage.de.gallery.GalleryFetcher;
import com.shaap.angelteichanlage.de.news.NewsFetcher;
import com.shaap.angelteichanlage.de.news.NewsreaderActivity;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showNewsreader (View view) {
        Intent intent = new Intent(this, NewsreaderActivity.class);
        startActivity(intent);
    }

    public void onClickGallery (View view) {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }

    public void onClickEvents (View view) {
        Intent intent = new Intent(this, EventsActivity.class);
        startActivity(intent);
    }

    public void doFetchNews (View view) {
        NewsFetcher nf = new NewsFetcher(this);
        GalleryFetcher gf = new GalleryFetcher(this);
        EventsFetcher  ef = new EventsFetcher(this);
        if (nf.Fetch()) {
            if (gf.Fetch()) {
                if (ef.Fetch()) {
                    Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,"FAIL!",Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this,"FAIL!",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"FAIL!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
