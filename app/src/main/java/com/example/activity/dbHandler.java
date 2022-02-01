package com.example.activity;

import java.util.ArrayList;

public class dbHandler {
    private static dbHandler dbHandlerSingleton;

    //CREATE A 2D ARRAYLIST THAT WILL BE GLOBALLY ACCESSIBLE BY GETTING THIS SINGLETON'S INSTANCE
    public ArrayList<ArrayList<String>> db = new ArrayList<ArrayList<String>>();

    //RETURN SINGLETON OBJECT
    public static dbHandler get() {
        //IF SINGLETON DOES NOT EXIST, CREATE
        if(dbHandlerSingleton == null) {
            dbHandlerSingleton = new dbHandler();
        }
        //OTHERWISE JUST GET THE VALUE
        return dbHandlerSingleton;
    }

    //PRIVATE CONSTRUCTOR
    private dbHandler() {}

    //USED FOR POPULATING HARDCODED ACCOUNTS ONLY [ARRAYLIST WILL BE FETCHED DIRECTLY]
    public void populate(String acc) {
        //SEPARATED BY | [ESCAPED FOR REGEX]
        String account[] = acc.split("\\|");
        db.add(new ArrayList<>());
        //GET LAST INDEX
        int last = db.size()-1;
        db.get(last).add(account[0]);
        db.get(last).add(account[1]);
        db.get(last).add(account[2]);
    }
}