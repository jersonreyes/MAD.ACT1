package com.example.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class AddEntry extends AppCompatActivity {
    Context con=this;
    ImageView ProfileLabel;
    Button datebtn;
    private static final int picCode = 1212;
    boolean birthdateSet=false;
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

        EditText Address = (EditText) findViewById(R.id.addEntryAddress);

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

        EditText OtherInfo = (EditText) findViewById(R.id.addEntryOtherInfo);

        Button Add = (Button) findViewById(R.id.addEntrySave);
        Button Cancel = (Button) findViewById(R.id.addEntryCancel);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender;
                if (isMale.isChecked())
                    gender = "Male";
                else if (isFemale.isChecked())
                    gender = "Female";
                else
                    gender = "Others";

                String toFill = "";
                GradientDrawable border = new GradientDrawable();
                border.setColor(0xFFFFFFFF); //white background
                border.setStroke(2, 0xFFFF0000);

                //CHECK FIRST IF ALL REQUIRED FIELDS ARE FILLED, IF FILLED THEN ADD
                if (name.getText().length() == 0) {
                    toFill += "\nName";
                    name.setBackgroundDrawable(border);
                }
                if (remark.getText().length() == 0) {
                    toFill += "\nRemarks";
                    remark.setBackgroundDrawable(border);
                }
                if (!birthdateSet) {
                    toFill += "\nBirthday";
                    datebtn.setBackgroundDrawable(border);
                }
                RadioGroup genderfield = (RadioGroup) findViewById(R.id.entryAddGender);
                if (genderfield.getCheckedRadioButtonId()==-1) {
                    toFill += "\nGender";
                    genderfield.setBackgroundDrawable(border);
                }
                if (!(c1.isChecked() || c2.isChecked() || c3.isChecked() || c4.isChecked() || c5.isChecked() ||
                        c6.isChecked() || c7.isChecked() || c8.isChecked() || c9.isChecked() || c10.isChecked())) {
                    toFill += "\nHobbies";
                    findViewById(R.id.addEntryHobbies).setBackgroundDrawable(border);
                    findViewById(R.id.addEntryHobbies).setPadding(5,5,5,5);
                }


                if (toFill.length() == 0) {
                    EntryList.cardlist.add(new EntryCards(
                            View.generateViewId(),
                            name.getText().toString(),
                            remark.getText().toString(),
                            R.drawable.profile_template,
                            datebtn.getText().toString(),
                            gender,
                            Address.getText().toString(),
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
                            c10.isChecked(),
                            OtherInfo.getText().toString()
                    ));

                    Intent toEntryList = new Intent(con, EntryList.class);
                    startActivity(toEntryList);
                }
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
                        birthdateSet=true;
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