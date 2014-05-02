package com.shaap.angelteichanlage.de.news;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaap.angelteichanlage.de.R;
import com.shaap.angelteichanlage.de.db.DatabaseHelper;

public class NewsArrayAdapter extends ArrayAdapter<NewsItem> {
    private final Context context;
    private final NewsItem[] values;

    public NewsArrayAdapter(Context context, NewsItem[] values) {
        super(context, R.layout.newsrowlayout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.newsrowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.newslabel);
        textView.setText(values[position].getTitle());
        if (!values[position].getIsRead()) textView.setTypeface(null,Typeface.BOLD);
        TextView textView2 = (TextView) rowView.findViewById(R.id.newssublabel);
        textView2.setText(Html.fromHtml(values[position].getDescription()));
        return rowView;
    }




}
