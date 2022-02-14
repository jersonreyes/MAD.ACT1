package com.example.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EntryList extends AppCompatActivity {
    //CONTEXT PARA SA INTENT
    Context con = this;


    private static ArrayList<String> CurrentAccount;

    static RecyclerView recycler;
    static RecyclerAdapter adapter;
    static List<EntryCards> cardlist = new ArrayList<EntryCards>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.entrylist);


        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        //CHECK THE INTENT FOR CHANGES
        Intent intents = getIntent();

        //USERNAME CHANGED = CHANGE CURRENT ACCOUNT
        if(getIntent().hasExtra("Username")){
            Prepopulate();
            for(ArrayList<String> account: MainActivity.db){
                if(account.get(0).toString().equals(intents.getExtras().getString("Username").toString())){
                    CurrentAccount=account;
                    break;
                }
            }
        }

        adapter = new RecyclerAdapter(this, cardlist);
        recycler.setAdapter(adapter);
        ImageButton LogoutButton = (ImageButton) findViewById(R.id.LogoutButton);
        ImageButton AddButton = (ImageButton) findViewById(R.id.AddButton);
        TextView UsernameLabel = (TextView) findViewById(R.id.UsernameLabel);
        UsernameLabel.setText(CurrentAccount.get(2));
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(con)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to Logout?")
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cardlist=new ArrayList<EntryCards>();
                                Intent toLogIn = new Intent(con, MainActivity.class);
                                startActivity(toLogIn);
                            }
                        })
                        .setPositiveButton("No", null);
                alert.show();
            }
        });
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAdd = new Intent(con, AddEntry.class);
                startActivity(toAdd);
            }
        });
    }

    public void Prepopulate(){
        cardlist.add(new EntryCards(View.generateViewId(), "Beluga", "hi :)", R.drawable.cat1, "12/23/2000", "Others",
                "Malolos, Bulacan", "0999-314-4141",
                true, false, false, true,false,false,true,false,true, false
        ,"I live in discord"));
        cardlist.add(new EntryCards(View.generateViewId(), "Skyflakes", "ngiti daw ako sabi ni mommy", R.drawable.cat2, "02/14/1990", "Male",
                "Apalit, Pampanga", "0991-388-9734",
                true, true, false, false,false,false,false,true,true, false
                ,"ehehehhe"));
        cardlist.add(new EntryCards(View.generateViewId(), "Chocnut", "I know exactly where u are", R.drawable.cat3, "07/18/1999", "Female",
                "Calumpit, Bulacan", "0992-414-8888",
                false, true, false, true,false,true,false,false,true, false
                ,"sus"));
        cardlist.add(new EntryCards(View.generateViewId(), "Mikmik", "Bleugh", R.drawable.cat4, "05/28/2005", "Male",
                "California, USA", "0912-424-6635",
                true, false, false, false,false,false,false,true,false, true
                ,"Magnanakaw ng ulam"));
        cardlist.add(new EntryCards(View.generateViewId(), "Creamer", "<.<", R.drawable.cat5, "10/10/2010", "Female",
                "Osaka, Japan", "0901-101-7630",
                false, false, false, true,false,false,false,true,false, false
                ,"Judger"));
        cardlist.add(new EntryCards(View.generateViewId(), "Cookie", "(:", R.drawable.cat6, "04/15/1997", "Others",
                "Seoul, Korea", "0922-222-2222",
                false, false, true, true,false,false,true,false,false, true
                ,"Friendly"));
        cardlist.add(new EntryCards(View.generateViewId(), "Choco", "gulat yarn", R.drawable.cat7, "12/02/2021", "Others",
                "Tondo, Manila", "0909-760-3109",
                false, false, false, true,false,true,false,true,false, true
                ,"Spooky"));
        cardlist.add(new EntryCards(View.generateViewId(), "Lala", "I believe I can flyyy", R.drawable.cat8, "11/19/2016", "Female",
                "Sydney, Australia", "0972-870-0000",
                true, false, false, false,true,false,false,false,true, true
                ,"Batman in past life"));
        cardlist.add(new EntryCards(View.generateViewId(), "Nestle", "Ni hao", R.drawable.cat9, "07/18/2015", "Others",
                "Beijing, China", "0999-666-6060",
                true, false, true, false,false,true,false,false,true, true
                ,"Lives on tupperwares"));
        cardlist.add(new EntryCards(View.generateViewId(), "Jin", "chill", R.drawable.cat10, "12/21/2012", "Male",
                "Land of Dawn", "0982-121-3570",
                false, false, false, true,false,false,false,false,false, false
                ,"Catnip for life"));
    }
    public void addCard(){

    }
}