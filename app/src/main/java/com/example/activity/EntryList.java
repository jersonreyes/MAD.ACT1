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

    RecyclerView recycler;
    RecyclerAdapter adapter;
    static List<EntryCards> cardlist = new ArrayList<EntryCards>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.entrylist);

        //CHECK THE INTENT FOR CHANGES
        Bundle intents = getIntent().getExtras();

        //USERNAME CHANGED = CHANGE CURRENT ACCOUNT
        if(intents.getString("Username").length()>0){
            for(ArrayList<String> account: MainActivity.db){
                if(account.get(0).toString().equals(intents.getString("Username").toString())){
                    CurrentAccount=account;
                    break;
                }
            }
        }


        ImageButton LogoutButton = (ImageButton) findViewById(R.id.LogoutButton);
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

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        for(int i=1;i<=10;i++) {
            cardlist.add(new EntryCards(i, "Test-"+String.valueOf(i), "asd", "dada"));
        }
        adapter = new RecyclerAdapter(this, cardlist);
        recycler.setAdapter(adapter);
    }
}