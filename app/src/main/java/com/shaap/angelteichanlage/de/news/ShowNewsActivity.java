package com.shaap.angelteichanlage.de.news;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import com.shaap.angelteichanlage.de.R;
import com.shaap.angelteichanlage.de.db.DatabaseHelper;

public class ShowNewsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        int newsID = getIntent().getIntExtra("id", -1);
        if (newsID != -1) {
            NewsDatabase dh = new NewsDatabase(this);
            NewsItem ni = dh.getItem(newsID);
            this.setTitle(ni.getTitle());
            WebView wv = (WebView) findViewById(R.id.webview);
            wv.loadData(ni.getDescription(),"text/html; charset=UTF-8", null);
            dh.close();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.show_news, menu);
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
        } else if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
