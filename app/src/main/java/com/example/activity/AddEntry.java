package com.example.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class AddEntry extends AppCompatActivity {
    Context con=this;
    ImageView ProfileLabel;
    Button datebtn;
    private static final int picCode = 1212;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.addentry);

        ProfileLabel = (ImageView) findViewById(R.id.addEntryProfileLabel);
        Button ProfileBtn = (Button) findViewById(R.id.addProfileBtn);
        ProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, picCode);
            }
        });

        EditText name = (EditText) findViewById(R.id.entryAddName);
        EditText remark = (EditText) findViewById(R.id.entryAddRemark);
        datebtn = (Button) findViewById(R.id.addEntryDate);

        RadioButton isMale = (RadioButton) findViewById(R.id.addEntrymale);
        RadioButton isFemale = (RadioButton) findViewById(R.id.addEntryfemale);
        RadioButton isOthers = (RadioButton) findViewById(R.id.addEntryothers);

        EditText Street = (EditText) findViewById(R.id.addEntryStreet);
        EditText HouseNumber = (EditText) findViewById(R.id.addEntryHouseNumber);

        AutoCompleteTextView Barangay = (AutoCompleteTextView) findViewById(R.id.addEntryBarangay);
        AutoCompleteTextView Municipality = (AutoCompleteTextView) findViewById(R.id.addEntryMunicipality);
        AutoCompleteTextView Province = (AutoCompleteTextView) findViewById(R.id.addEntryProvince);

        EditText Phone = (EditText) findViewById(R.id.addEntryNumber);

        CheckBox c1 = (CheckBox) findViewById(R.id.addEntrych1);
        CheckBox c2 = (CheckBox) findViewById(R.id.addEntrych2);
        CheckBox c3 = (CheckBox) findViewById(R.id.addEntrych3);
        CheckBox c4 = (CheckBox) findViewById(R.id.addEntrych4);
        CheckBox c5 = (CheckBox) findViewById(R.id.addEntrych5);
        CheckBox c6 = (CheckBox) findViewById(R.id.addEntrych6);
        CheckBox c7 = (CheckBox) findViewById(R.id.addEntrych7);
        CheckBox c8 = (CheckBox) findViewById(R.id.addEntrych8);
        CheckBox c9 = (CheckBox) findViewById(R.id.addEntrych9);
        CheckBox c10 = (CheckBox) findViewById(R.id.addEntrych10);

        Button Add = (Button) findViewById(R.id.addEntrySave);
        Button Cancel = (Button) findViewById(R.id.addEntryCancel);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender;
                if(isMale.isChecked())
                    gender="Male";
                else if (isFemale.isChecked())
                    gender="Female";
                else
                    gender="Others";

                EntryList.cardlist.add(new EntryCards(
                        View.generateViewId(),
                        name.getText().toString(),
                        remark.getText().toString(),
                        R.drawable.profile_template,
                        datebtn.getText().toString(),
                        gender,
                        Street.getText().toString(),
                        HouseNumber.getText().toString(),
                        Barangay.getText().toString(),
                        Municipality.getText().toString(),
                        Province.getText().toString(),
                        Phone.getText().toString(),
                        c1.isChecked(),
                        c2.isChecked(),
                        c3.isChecked(),
                        c4.isChecked(),
                        c5.isChecked(),
                        c6.isChecked(),
                        c7.isChecked(),
                        c8.isChecked(),
                        c9.isChecked(),
                        c10.isChecked()
                ));

                Intent toEntryList = new Intent(con, EntryList.class);
                startActivity(toEntryList);
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toEntryList = new Intent(con, EntryList.class);
                startActivity(toEntryList);
            }
        });
    }

    public void dateDialog(View view) {
        int day = 1, month = 0, year = 1990;
        final DatePickerDialog datePickerDialog = new DatePickerDialog(con, R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        datebtn.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, year, month, day);

        datePickerDialog.getWindow().setBackgroundDrawableResource(R.drawable.rounded_corners_plain);
        datePickerDialog.show();

    }

    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        super.onActivityResult(reqCode, resCode, data);
        if (reqCode == picCode) {
            Bitmap pic = (Bitmap) data.getExtras().get("data");
            ProfileLabel.setImageBitmap(pic);
        }
    }
}