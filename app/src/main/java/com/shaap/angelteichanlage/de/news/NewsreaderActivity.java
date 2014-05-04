package com.shaap.angelteichanlage.de.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.app.*;
import android.view.View;
import android.widget.ListView;

import com.shaap.angelteichanlage.de.R;

import java.util.List;

public class NewsreaderActivity extends ListActivity {

    private NewsArrayAdapter nia;
    private NewsDatabase ndb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsreader);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        ndb = new NewsDatabase(this);
        showNews();
    }

    private void showNews() {

        nia = new NewsArrayAdapter(this,ndb.getAll().toArray(new NewsItem[0]));
        this.setListAdapter(nia);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.newsreader, menu);
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
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        int newsid = nia.getItem(position).get_id();
        ndb.setNewsRead(newsid,true);
        Intent intent = new Intent(this, ShowNewsActivity.class);
        intent.putExtra("id",newsid);
        startActivity(intent);
        //do something here using the position in the array
    }
    @Override
    public void onResume()
    {
        if (nia != null) {
            showNews();
        }

        super.onResume();
    }

}
