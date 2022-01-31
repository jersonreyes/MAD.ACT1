package com.example.activity;

import java.util.ArrayList;

public class dbHandler {
    private static dbHandler dbHandlerSingleton;

    private ArrayList<String> db = new ArrayList<String>();

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
    private dbHandler() {
        //POPULATE HARDCODED ACCOUNTS
        //USERNAME AND PASSWORD SEPARATED BY COMMA (String.split(",") -> returns an array) TO ACCESS
        db.add("Anna,13579abcdeA");
        db.add("Lorna,Th3Q41ckBr0wnF0x");
        db.add("Fe,p@zzW0rd");
    }

    //USED TO ADD ACCOUNTS (SEMI-SETTER)
    public void addAccount(String acc) {
        db.add(acc);
    }

    //USED TO RETURN ACCOUNTS (GETTER)
    public ArrayList<String> getAccounts() {
        return db;
    }
}