package com.example.nomv20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class NewItemActivity extends AppCompatActivity {

    EditText produce_query;
    Button confirm_button;
    File file;
    DatePicker datePicker;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        produce_query = findViewById(R.id.produce_query);
        confirm_button = findViewById(R.id.confirm_button);
        calendar = Calendar.getInstance();

        datePicker = (DatePicker) findViewById(R.id.simpleDatePicker);
        datePicker.setEnabled(true);


        file =new File(this.getFilesDir(), VegetableBasketActivity.fileName);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = (int) (dm.widthPixels * 0.9);
        int height = (int) (dm.heightPixels * 0.3);
        getWindow().setLayout(width, height);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = produce_query.getText().toString();
                Date d = calendar.getTime();
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

    public void writeToFile(String data, Context context) {
        //String existing = readFromFile(context);
        try (BufferedWriter fos = new BufferedWriter(new FileWriter(context.getFileStreamPath(VegetableBasketActivity.fileName), true))) {

            fos.write(data + "\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}