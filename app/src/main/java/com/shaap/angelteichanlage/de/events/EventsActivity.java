package com.shaap.angelteichanlage.de.events;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.shaap.angelteichanlage.de.R;

public class EventsActivity extends ListActivity {


    private EventsArrayAdapter ea;
    private EventsDatabase edb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        edb = new EventsDatabase(this);
        showEvents();
    }

    private void showEvents() {

        ea = new EventsArrayAdapter(this,edb.getAll().toArray(new EventsItem[0]));
        this.setListAdapter(ea);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.events, menu);
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
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        int eventid = ea.getItem(position).getId();
        Intent intent = new Intent(this, EventDetailsActivity.class);
        intent.putExtra("id",eventid);
        startActivity(intent);
        //do something here using the position in the array
    }
}
