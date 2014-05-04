package com.shaap.angelteichanlage.de.gallery;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fedorvlasov.lazylist.ImageLoader;
import com.shaap.angelteichanlage.de.R;


public class PhotoActivity extends Activity {

    public ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        imageLoader=new ImageLoader(this.getApplicationContext());
        int photoID = getIntent().getIntExtra("id", -1);
        if (photoID != -1) {
            GalleryDatabase dh = new GalleryDatabase(this);
            GalleryItem gi = dh.getItem(photoID);
            ImageView imageview = (ImageView) findViewById(R.id.imageView);
            TextView textview = (TextView) findViewById(R.id.textView);
            imageLoader.DisplayImage(gi.getURL(), imageview);
            textview.setText(gi.getText());
            dh.close();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.photo, menu);
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
