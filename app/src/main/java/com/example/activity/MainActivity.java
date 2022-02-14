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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    String username_string, password_string;
    boolean found = false;
    static boolean populated = false;

    //CONTEXT PARA SA INTENT
    Context con = this;

    // SINGLETON IMPLEMENTATION REMOVED | MADE ARRAYLIST PUBLIC AND STATIC | BAWAS CODE
    //2D ARRAYLIST IMPLEMENTATION UPDATE
    public static ArrayList<ArrayList<String>> db = new ArrayList<ArrayList<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //POPULATE HARDCODED ACCOUNTS
        //USERNAME AND PASSWORD SEPARATED BY COMMA (String.split(",") -> returns an array) TO ACCESS
        //RUN ONLY ONCE
        if(!populated) {
            populate("Anna|13579abcdeA|Anna Lisa");
            populate("Lorna|Th3Q41ckBr0wnF0x|Lorna Dee");
            populate("Fe|p@zzW0rd|Fe Rari");
            populate("a|a|ab cd");
            populated = true;
        }

        //SET STATUSBAR AS TRANSAPRENT
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);

        //ANIMATE
        LinearLayout background, background_accent, login_ctr;
        background_accent = findViewById(R.id.background_accent);
        background = findViewById(R.id.background);
        login_ctr = findViewById(R.id.login_ctr);
        TextView welcome = findViewById(R.id.welcome);
        ImageView logo = findViewById(R.id.logo);

        Animation slide_left, slide_top, slide_bottom, fade, random;
        slide_left = AnimationUtils.loadAnimation(this, R.anim.anim_slide_left);
        fade = AnimationUtils.loadAnimation(this, R.anim.fade);
        slide_bottom = AnimationUtils.loadAnimation(this, R.anim.anim_slide_bottom);
        random = AnimationUtils.loadAnimation(this, R.anim.random);

        //SET ANIMATIONS
        welcome.setAnimation(fade);
        logo.setAnimation(slide_bottom);
        background_accent.setAnimation(slide_left);
        background.setAnimation(random);
        login_ctr.setAnimation(fade);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) {
                    if(verify_rec("username"))
                        username.setCompoundDrawablesWithIntrinsicBounds(R.drawable.person, 0, R.drawable.check, 0);
                    else
                        username.setCompoundDrawablesWithIntrinsicBounds(R.drawable.person, 0, 0, 0);
                }
            }
        });

    }

    public void login_verify(View view)  {
        if(!TextUtils.isEmpty(username.getText().toString()) && !TextUtils.isEmpty(password.getText().toString()))
            if(verify_rec("combination")){
                Toast.makeText(this,"Successful login",Toast.LENGTH_SHORT).show();
                Intent toEntryList = new Intent(this, EntryList.class);
                //SINCE IT'S BEEN VERIFIED, PASS ONLY THE USERNAME, AND USE THE USERNAME TO GET THE DATA ON THE ENTRYLIST.CLASS
                toEntryList.putExtra("Username", username.getText().toString());
                startActivity(toEntryList);
            }else {
                Builder alert = new AlertDialog.Builder(this)
                        .setTitle("Login")
                        .setMessage("The username and password did not match our records. Try again.")
                        .setPositiveButton("Okay",null);
                alert.show();
            }
    }

    public void registration_transfer(View view) {
        Builder alert = new AlertDialog.Builder(this)
                .setTitle("Register")
                .setMessage("Redirecting you to the registration screen.")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(con, Registration.class);
                        startActivity(i);
                    }
                })
                .setIcon(R.drawable.person);
        alert.show();

    }

    //VERIFY IF USERNAME EXISTS OR VERIFY COMBINATION OF USERNAME AND PASSWORD
    public boolean verify_rec(String type) {
        if(type=="username") {
            for (int i = 0; i < db.size(); i++)
                if (username.getText().toString().equals(db.get(i).get(0)))
                        return true;
        }   else {
            for (int i = 0; i < db.size(); i++)
                if (verify_rec("username") && password.getText().toString().equals(db.get(i).get(1)))
                    return true;
        }
        return false;
    }

    //USED FOR POPULATING HARDCODED ACCOUNTS ONLY
    public void populate(String acc) {
        //SEPARATED BY | [ESCAPED FOR REGEX]
        String[] account = acc.split("\\|");
        db.add(new ArrayList<>());
        //GET LAST INDEX
        int last = db.size()-1;
        db.get(last).add(account[0]);
        db.get(last).add(account[1]);
        db.get(last).add(account[2]);
    }

}