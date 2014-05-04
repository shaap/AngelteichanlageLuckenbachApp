package com.shaap.angelteichanlage.de.gallery;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fedorvlasov.lazylist.ImageLoader;
import com.shaap.angelteichanlage.de.R;

/**
 * Created by Sebastian on 03.05.2014.
 */
public class GalleryAdapter extends BaseAdapter {

    private Activity activity;
    private GalleryItem[] data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;

    public GalleryAdapter(Activity a, GalleryItem[] d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.length;
    }

    public Object getItem(int position) {
        return data[position];
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.item, null);
        ImageView image=(ImageView)vi.findViewById(R.id.image);
        imageLoader.DisplayImage(data[position].getURL(), image);
        return vi;
    }
}
