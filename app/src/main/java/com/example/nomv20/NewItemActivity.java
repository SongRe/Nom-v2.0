package com.example.nomv20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NewItemActivity extends AppCompatActivity {

    EditText produce_query;
    ImageButton confirm_button;
    File file;
    DatePicker datePicker;
    private Calendar calendar;
    private ArrayList<ProduceSample> produceSamples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        produce_query = findViewById(R.id.produce_query);
        produce_query.addTextChangedListener(checkInput);
        confirm_button = findViewById(R.id.confirm_button);
        calendar = Calendar.getInstance();

        datePicker = (DatePicker) findViewById(R.id.simpleDatePicker);
        datePicker.setEnabled(true);

        file =new File(this.getFilesDir(), VegetableBasketActivity.fileName);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);



        Intent getIntent = getIntent();
        produceSamples = getIntent.getParcelableArrayListExtra(MainActivity.PRODUCE_SAMPLE_CODE);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = produce_query.getText().toString(); //the name of vegetable
                Date d = calendar.getTime(); //current date
                if(isInDatabase(s)) {
                    int i = databaseStringIndex(s);
                    int expDays = produceSamples.get(i).getDays();  //finding the expiry date
                    calendar.add(Calendar.DATE, expDays);
                    Date expDate = calendar.getTime(); //getting the expiry date
                    Vegetable newVegetable = new Vegetable(s, expDate,d, false );
                    writeFileOnInternalStorage(NewItemActivity.this, VegetableBasketActivity.fileName, newVegetable.toString());
                    Toast.makeText(NewItemActivity.this, "Success!", Toast.LENGTH_LONG).show();
//                    Intent mainIntent = new Intent(NewItemActivity.this, MainActivity.class);
//                    startActivity(mainIntent);
                    startActivity(getIntent());
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                } else {
                    Toast.makeText(NewItemActivity.this, "Produce ID not found. Please make sure you have spelt it correctly", Toast.LENGTH_LONG).show();
                }




            }
        });
    }


    private TextWatcher checkInput = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String s = produce_query.getText().toString();
            confirm_button.setEnabled(!s.isEmpty()); //enable roundedbutton if something is in the editText
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void writeFileOnInternalStorage(Context mcoContext, String sFileName, String sBody){
        File dir = new File(mcoContext.getFilesDir(), "Nom V2.0");
        if(!dir.exists()){
            dir.mkdir();
        }

        try {
            File gpxfile = new File(dir, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isInDatabase(String s) {
        for(int i = 0; i < produceSamples.size();i++) {
            if(s.equalsIgnoreCase(produceSamples.get(i).getProduce())) {
                return true;
            }
        }
        return false;
    }

    public int databaseStringIndex(String s) {
        for(int i = 0; i < produceSamples.size();i++) {
            if(s.equalsIgnoreCase(produceSamples.get(i).getProduce())) {
                return i;
            }
        }
        return -1;
    }
}