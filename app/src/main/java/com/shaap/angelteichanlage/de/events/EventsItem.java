package com.shaap.angelteichanlage.de.events;

import java.util.Date;

/**
 * Created by Sebastian on 04.05.2014.
 */
public class EventsItem {
    int id;
    String Name;
    String Description;
    Date Start;
    Date End;
    String Icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }



    public EventsItem() {
    }

    public EventsItem(int id, String name, String description, Date start, Date end, String icon) {
        this.id = id;
        Name = name;
        Description = description;
        Start = start;
        End = end;
        Icon = icon;
    }
}
