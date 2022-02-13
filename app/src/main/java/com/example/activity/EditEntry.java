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

import java.sql.Array;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class EditEntry extends AppCompatActivity {
    Context con=this;
    ImageView ProfileLabel;
    int month,day,year;
    Button datebtn;
    private static final int picCode = 1313;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.editentry);

        ProfileLabel = (ImageView) findViewById(R.id.entryProfileLabel);
        Button ProfileBtn = (Button) findViewById(R.id.editProfileBtn);
        ProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, picCode);
            }
        });

        Bundle intent = getIntent().getExtras();
        int key = Integer.parseInt(intent.getString("Position"));
        EditText name = (EditText) findViewById(R.id.entryEditName);
        EditText remark = (EditText) findViewById(R.id.entryEditRemark);
        datebtn = (Button) findViewById(R.id.editDate);

        RadioButton isMale = (RadioButton) findViewById(R.id.male);
        RadioButton isFemale = (RadioButton) findViewById(R.id.female);
        RadioButton isOthers = (RadioButton) findViewById(R.id.others);

        EditText Street = (EditText) findViewById(R.id.editEntryStreet);
        EditText HouseNumber = (EditText) findViewById(R.id.editEntryHouseNumber);

        AutoCompleteTextView Barangay = (AutoCompleteTextView) findViewById(R.id.editEntryBarangay);
        AutoCompleteTextView Municipality = (AutoCompleteTextView) findViewById(R.id.editEntryMunicipality);
        AutoCompleteTextView Province = (AutoCompleteTextView) findViewById(R.id.editEntryProvince);

        EditText Phone = (EditText) findViewById(R.id.editEntryNumber);

        CheckBox c1 = (CheckBox) findViewById(R.id.editEntrych1);
        CheckBox c2 = (CheckBox) findViewById(R.id.editEntrych2);
        CheckBox c3 = (CheckBox) findViewById(R.id.editEntrych3);
        CheckBox c4 = (CheckBox) findViewById(R.id.editEntrych4);
        CheckBox c5 = (CheckBox) findViewById(R.id.editEntrych5);
        CheckBox c6 = (CheckBox) findViewById(R.id.editEntrych6);
        CheckBox c7 = (CheckBox) findViewById(R.id.editEntrych7);
        CheckBox c8 = (CheckBox) findViewById(R.id.editEntrych8);

        Button Save = (Button) findViewById(R.id.editEntrySave);
        Button Cancel = (Button) findViewById(R.id.editEntryCancel);

        //SETUP THE ENTRY
        name.setText(EntryList.cardlist.get(key).getName());
        remark.setText(EntryList.cardlist.get(key).getDescription());
        datebtn.setText(EntryList.cardlist.get(key).getBirthdate());
        String[] dates = EntryList.cardlist.get(key).getBirthdate().split("/");
        month=Integer.parseInt(dates[0]);
        day=Integer.parseInt(dates[1]);
        year=Integer.parseInt(dates[2]);

        if(EntryList.cardlist.get(key).getGender()=="Male")
            isMale.setChecked(true);
        else if(EntryList.cardlist.get(key).getGender()=="Female")
            isFemale.setChecked(true);
        else if(EntryList.cardlist.get(key).getGender()=="Others")
            isOthers.setChecked(true);

        Street.setText(EntryList.cardlist.get(key).getStreet());
        HouseNumber.setText(EntryList.cardlist.get(key).getHouseNumber());
        Barangay.setText(EntryList.cardlist.get(key).getBarangay());
        Municipality.setText(EntryList.cardlist.get(key).getMunicipality());
        Province.setText(EntryList.cardlist.get(key).getProvince());
        Phone.setText(EntryList.cardlist.get(key).getPhone());

        if(EntryList.cardlist.get(key).isC1())
            c1.setChecked(true);
        if(EntryList.cardlist.get(key).isC2())
            c2.setChecked(true);
        if(EntryList.cardlist.get(key).isC3())
            c3.setChecked(true);
        if(EntryList.cardlist.get(key).isC4())
            c4.setChecked(true);
        if(EntryList.cardlist.get(key).isC5())
            c5.setChecked(true);
        if(EntryList.cardlist.get(key).isC6())
            c6.setChecked(true);
        if(EntryList.cardlist.get(key).isC7())
            c7.setChecked(true);
        if(EntryList.cardlist.get(key).isC8())
            c8.setChecked(true);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender;
                if(isMale.isChecked())
                    gender="Male";
                else if (isFemale.isChecked())
                    gender="Female";
                else
                    gender="Others";

                //Overwrite the current card
                EntryList.cardlist.get(key).setName(name.getText().toString());
                EntryList.cardlist.get(key).setDescription(remark.getText().toString());
                EntryList.cardlist.get(key).setBirthdate(datebtn.getText().toString());
                EntryList.cardlist.get(key).setGender(gender);
                EntryList.cardlist.get(key).setStreet(Street.getText().toString());
                EntryList.cardlist.get(key).setHouseNumber(HouseNumber.getText().toString());
                EntryList.cardlist.get(key).setBarangay(Barangay.getText().toString());
                EntryList.cardlist.get(key).setMunicipality(Municipality.getText().toString());
                EntryList.cardlist.get(key).setProvince(Province.getText().toString());
                EntryList.cardlist.get(key).setPhone(Phone.getText().toString());
                EntryList.cardlist.get(key).setC1(c1.isChecked());
                EntryList.cardlist.get(key).setC2(c2.isChecked());
                EntryList.cardlist.get(key).setC3(c3.isChecked());
                EntryList.cardlist.get(key).setC4(c4.isChecked());
                EntryList.cardlist.get(key).setC5(c5.isChecked());
                EntryList.cardlist.get(key).setC6(c6.isChecked());
                EntryList.cardlist.get(key).setC7(c7.isChecked());
                EntryList.cardlist.get(key).setC8(c8.isChecked());

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