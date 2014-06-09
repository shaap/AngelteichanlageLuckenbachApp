package com.shaap.proevents.types;

/**
 * Created by Sebastian on 25.05.2014.
 */
public class PlaceItem {
    int id;
    int x;
    int y;
    Boolean enabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public PlaceItem(int id, int x, int y, Boolean enabled) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.enabled = enabled;
    }

    public PlaceItem() {
    }
}
