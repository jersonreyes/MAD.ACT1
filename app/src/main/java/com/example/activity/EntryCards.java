package com.example.activity;

import java.util.Map;

public class EntryCards {
    private int ID;
    private String Name;
    private String Description;
    private String Picture;

    public EntryCards(int ID, String Name, String Description, String Picture){
        this.ID=ID;
        this.Name=Name;
        this.Description=Description;
        this.Picture=Picture;
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

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
