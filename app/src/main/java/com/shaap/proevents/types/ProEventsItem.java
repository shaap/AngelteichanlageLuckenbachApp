package com.shaap.proevents.types;

import android.text.method.DateTimeKeyListener;

import java.util.Currency;
import java.util.Date;
import java.util.List;

/**
 * Created by Sebastian on 25.05.2014.
 */
public class ProEventsItem {
    int id;
    String Title;
    String Description;
    Date Start;
    Date End;
    int Price;
    int Room;
    int Height;
    int Width;
    List<PlaceItem> Places;

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public List<PlaceItem> getPlaces() {
        return Places;
    }

    public void setPlaces(List<PlaceItem> places) {
        Places = places;
    }

    public int getRoom() {
        return Room;
    }

    public void setRoom(int room) {
        Room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getStart() {
        return Start;
    }

    public void setStart(Date start) {
        Start = start;
    }

    public Date getEnd() {
        return End;
    }

    public void setEnd(Date end) {
        End = end;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public ProEventsItem(int id, String title, String description, Date start, Date end, int price, int room, List<PlaceItem> places, int width, int height) {
        this.id = id;
        Title = title;
        Description = description;
        Start = start;
        End = end;
        Price = price;
        Room=room;
        Places = places;
        Height = height;
        Width = width;
    }

    public ProEventsItem() {
    }
}
