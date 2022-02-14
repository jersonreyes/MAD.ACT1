package com.example.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewEntry extends AppCompatActivity {
    Context con=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.entryview);

        Bundle intent = getIntent().getExtras();
        int key = Integer.parseInt(intent.getString("Position"));
        EntryCards acc = EntryList.cardlist.get(key);

        ImageView Profile = (ImageView) findViewById(R.id.viewProfile);
        ImageButton backBtn = (ImageButton) findViewById(R.id.viewBackButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(con, EntryList.class));
            }
        });
        TextView Name = (TextView) findViewById(R.id.viewName);
        TextView Remarks = (TextView) findViewById(R.id.viewRemarks);
        TextView Gender  = (TextView) findViewById(R.id.viewGender);
        TextView Phone = (TextView) findViewById(R.id.viewPhone);
        TextView Birthdate = (TextView) findViewById(R.id.viewBirthdate);
        TextView Address = (TextView) findViewById(R.id.viewStreet);
        TextView Hobbies = (TextView) findViewById(R.id.viewHobbies);
        TextView OtherInfo = (TextView) findViewById(R.id.viewOtherInfo);

        Profile.setImageResource(acc.getPicture());
        Name.setText(acc.getName());
        Remarks.setText(acc.getDescription());
        Gender.setText(acc.getGender());
        Phone.setText(acc.getPhone());
        Birthdate.setText(acc.getBirthdate());
        Address.setText(acc.getAddress());
        String hobbies="";
        if(acc.isC1())
            hobbies+="\nSinging";
        if(acc.isC2())
            hobbies+="\nPlaying Guitar";
        if(acc.isC3())
            hobbies+="\nPlaying Games";
        if(acc.isC4())
            hobbies+="\nDancing";
        if(acc.isC5())
            hobbies+="\nPainting";
        if(acc.isC6())
            hobbies+="\nReading";
        if(acc.isC7())
            hobbies+="\nBaking";
        if(acc.isC8())
            hobbies+="\nGardening";
        if(acc.isC9())
            hobbies+="\nWriting";
        if(acc.isC10())
            hobbies+="\nCooking";
        if(hobbies.length()>2)
        Hobbies.setText(hobbies.substring(1));
        OtherInfo.setText(acc.getOtherInfo());
    }


}