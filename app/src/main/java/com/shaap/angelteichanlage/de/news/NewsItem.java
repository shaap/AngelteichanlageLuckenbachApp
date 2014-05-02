package com.shaap.angelteichanlage.de.news;

/**
 * Created by Sebastian on 02.05.14.
 */
public class NewsItem {
    int _id;
    String Title;
    String Description;
    Boolean IsRead;

    public NewsItem() {
    }

    public NewsItem(int _id, String title, String description, Boolean isRead) {
        this._id = _id;
        Title = title;
        Description = description;
        IsRead = isRead;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    public Boolean getIsRead() {
        return IsRead;
    }

    public void setIsRead(Boolean isRead) {
        IsRead = isRead;
    }
}
