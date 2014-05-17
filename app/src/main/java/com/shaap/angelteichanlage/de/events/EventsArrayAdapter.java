package com.shaap.angelteichanlage.de.events;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fedorvlasov.lazylist.ImageLoader;
import com.shaap.angelteichanlage.de.R;
import com.shaap.angelteichanlage.de.util.ISO8601DateParser;

/**
 * Created by Sebastian on 04.05.2014.
 */
public class EventsArrayAdapter extends ArrayAdapter<EventsItem> {
    private final Context context;
    private final EventsItem[] values;
    public ImageLoader imageLoader;
    public EventsArrayAdapter(Context context, EventsItem[] values) {
        super(context, R.layout.eventsrowlayout, values);
        this.context = context;
        this.values = values;
        imageLoader=new ImageLoader(context.getApplicationContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.eventsrowlayout, parent, false);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        if (!values[position].getIcon().equals("")) {
            try {
                imageLoader.DisplayImage(values[position].getIcon(), imageView);
            } catch (Exception ex) {

            }
        }
        TextView textView = (TextView) rowView.findViewById(R.id.textView);
        textView.setText(values[position].getName());
        textView.setTypeface(null, Typeface.BOLD);
        rowView.setBackgroundColor(Color.argb(50, 128, 128, 128));
        TextView textView2 = (TextView) rowView.findViewById(R.id.textView2);
        textView2.setText(ISO8601DateParser.toString(values[position].getStart()));
        return rowView;
    }
}
