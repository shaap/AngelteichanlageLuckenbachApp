package com.shaap.angelteichanlage.de.gallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.shaap.angelteichanlage.de.R;
import com.shaap.angelteichanlage.de.news.NewsArrayAdapter;
import com.shaap.angelteichanlage.de.news.NewsDatabase;
import com.shaap.angelteichanlage.de.news.NewsItem;

import java.util.List;


public class GalleryActivity extends Activity {

    private GalleryAdapter ga;
    private GalleryDatabase gdb;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        context = this;
        List<GalleryItem> gi;
        gdb = new GalleryDatabase(this);
        gi = gdb.getAll();
        ga = new GalleryAdapter(this,gi.toArray(new GalleryItem[0]));


        GridView gv = (GridView) findViewById(R.id.gridView);
        gv.setAdapter(ga);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            parent.getItemAtPosition(position);
                GalleryItem git = (GalleryItem)ga.getItem(position);
                Intent intent = new Intent(context, PhotoActivity.class);
                intent.putExtra("id",git.getId());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gallery, menu);
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
