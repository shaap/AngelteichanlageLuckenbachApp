package com.shaap.angelteichanlage.de.gallery;

/**
 * Created by Sebastian on 03.05.2014.
 */
public class GalleryItem {
    String URL;
    int Timestamp;
    String Text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(int timestamp) {
        Timestamp = timestamp;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public GalleryItem() {
    }

    public GalleryItem(int id, String URL, int timestamp, String text) {
        this.URL = URL;
        Timestamp = timestamp;
        Text = text;
        this.id = id;
    }
}
