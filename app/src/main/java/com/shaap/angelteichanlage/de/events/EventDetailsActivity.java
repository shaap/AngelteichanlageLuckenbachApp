package com.shaap.angelteichanlage.de.events;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fedorvlasov.lazylist.ImageLoader;
import com.shaap.angelteichanlage.de.R;
import com.shaap.angelteichanlage.de.util.CalendarAdder;
import com.shaap.angelteichanlage.de.util.ISO8601DateParser;

public class EventDetailsActivity extends Activity {
    private EventsItem ei;
    private ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        imageLoader=new ImageLoader(this.getApplicationContext());
        int eventsID = getIntent().getIntExtra("id", -1);
        if (eventsID != -1) {
            EventsDatabase dh = new EventsDatabase(this);
            ei = dh.getItem(eventsID);
            this.setTitle(ei.getName());
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            TextView tvDate = (TextView) findViewById(R.id.textView2);
            TextView tvTime = (TextView) findViewById(R.id.textView4);
            WebView wvDesc = (WebView) findViewById(R.id.webview);
            if (!ei.getIcon().equals("")) imageLoader.DisplayImage(ei.getIcon(),imageView);
            tvDate.setText(ISO8601DateParser.dateString(ei.getStart()));
            tvTime.setText(String.format("%s - %s", ISO8601DateParser.timeString(ei.getStart()), ISO8601DateParser.timeString(ei.getEnd())));
            wvDesc.loadData(ei.getDescription(),"text/html; charset=utf-8", "utf-8");
            dh.close();


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event_details, menu);
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

    public void onClickAddCal (View view) {
        CalendarAdder.AddToCalendar(this,ei);
    }
}
