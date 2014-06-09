package com.shaap.proevents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fedorvlasov.lazylist.ImageLoader;
import com.shaap.angelteichanlage.de.R;
import com.shaap.angelteichanlage.de.util.ISO8601DateParser;
import com.shaap.proevents.types.PlaceItem;
import com.shaap.proevents.types.ProEventsItem;

import java.util.List;

public class Booking1Activity extends Activity {
    private ImageLoader imageLoader;
    private ProEventsItem peiTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking1);
        imageLoader=new ImageLoader(this.getApplicationContext());
        int eventsID = -1;

        final Intent intent = getIntent();
        final String action = intent.getAction();

        if (Intent.ACTION_VIEW.equals(action)) {
            try {
            eventsID = Integer.parseInt(intent.getData().getQueryParameter("event"));
            } catch (Exception ex) {

            }
        }
        if (eventsID != -1) {
            peiTemp = ProEventsWeb.getEvent(eventsID);
            this.setTitle(peiTemp.getTitle());
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageLoader.DisplayImage( ProEventsWeb.EVENTS_MAP_URL + "?room=" + peiTemp.getRoom() ,imageView);



        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.booking1, menu);
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
