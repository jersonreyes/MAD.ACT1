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
            for(int i=1;i<=10;i++) {
                cardlist.add(new EntryCards(i, "test", "desc", "asd", "09/05/2001", "Male",
                        "street", "12", "bar","mun","Pro","09998",
                        true, true, true, true,true,true,true,true));
            }

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
                CurrentAccount = null;
                Intent toLogIn = new Intent(con, MainActivity.class);
                startActivity(toLogIn);
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

    public void addCard(){

    }
}