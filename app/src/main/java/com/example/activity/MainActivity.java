package com.example.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText username, password;
    String username_string, password_string;
    boolean found = false;

    //CONTEXT PARA SA INTENT
    Context con = this;

    //CREATE AN ARRAYLIST THAT WILL BE POPULATED BY DATA FROM THE ARRAYLIST FROM DBHANDLER SINGLETON
    ArrayList<String> login_cred = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        //RUNTIME SINGLETON IMPLEMENTATION TO MAKE DB ARRAYLIST ACCESSIBLE BY THE ENTIRE APP
        //GET INSTANCE - POPULATE LOGIN_CRED ARRAYLIST FROM THE DATA THAT IS STORED TO THE SINGLE ARRAYLIST FROM DBHANDLER SINGLETON
        login_cred = dbHandler.get().getAccounts();

        //SET STATUSBAR TO TRANSAPRENT
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);

        VideoView videoview = (VideoView) findViewById(R.id.introbg);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.intro);
        videoview.setDrawingCacheEnabled(true);
        videoview.setVideoURI(uri);
        videoview.start();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                int videoWidth = mp.getVideoWidth();
                int videoHeight = mp.getVideoHeight();

                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int screenWidth = displayMetrics.widthPixels;
                int screenHeight = displayMetrics.heightPixels;

                float scaleY = 1.0f;
                float scaleX = (videoWidth * screenHeight / videoHeight) / screenWidth;

                int pivotPointX = (int) (screenWidth / 2);
                int pivotPointY = (int) (screenHeight / 2);

                videoview.setScaleX(scaleX);
                videoview.setScaleY(scaleY);
                videoview.setPivotX(pivotPointX);
                videoview.setPivotY(pivotPointY);

                mp.setLooping(true);
                mp.start();
            }
        });

        //ANIMATE
        LinearLayout background, login_ctr;
        TextView background_accent;
        background_accent = findViewById(R.id.welcome2);
        //background = findViewById(R.id.background);
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
        //background.setAnimation(random);
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
            if(verify_rec("combination"))
                Toast.makeText(this,"Successful login",Toast.LENGTH_SHORT).show();
            else {
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
            for (int i = 0; i < login_cred.size(); i++)
                if (username.getText().toString().equals(login_cred.get(i).split(",")[0]))
                    return true;
        }   else {
            for (int i = 0; i < login_cred.size(); i++)
                if (username.getText().toString().equals(login_cred.get(i).split(",")[0]) && password.getText().toString().equals(login_cred.get(i).split(",")[1]))
                    return true;
        }
        return false;
    }


}