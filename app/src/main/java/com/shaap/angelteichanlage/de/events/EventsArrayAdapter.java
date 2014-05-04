package com.shaap.angelteichanlage.de.events;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shaap.angelteichanlage.de.R;

/**
 * Created by Sebastian on 04.05.2014.
 */
public class EventsArrayAdapter extends ArrayAdapter<EventsItem> {
    private final Context context;
    private final EventsItem[] values;
// TODO: change newsrowlayout to eventsrowlayout
    public EventsArrayAdapter(Context context, EventsItem[] values) {
        super(context, R.layout.newsrowlayout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.newsrowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textView);
        textView.setText(values[position].getName());
        textView.setTypeface(null, Typeface.BOLD);
        rowView.setBackgroundColor(Color.argb(50, 128, 128, 128));
        TextView textView2 = (TextView) rowView.findViewById(R.id.newssublabel);
        textView2.setText(values[position].getStart().toString());
        return rowView;
    }
}
