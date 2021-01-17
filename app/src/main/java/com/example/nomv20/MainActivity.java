package com.example.nomv20;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.view.View;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.mlkit.vision.common.InputImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.net.ssl.ManagerFactoryParameters;


public class MainActivity extends AppCompatActivity {


    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    public static final String URI_CODE = "com.example.nomv20.ImageURI";
    public static final String PRODUCE_SAMPLE_CODE = "com.example.nomv20.ProduceSample";
    File file;

    private ImageButton button;
    private Button imgButton;
    private ArrayList<ProduceSample> produceSamples= new ArrayList<>();
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendar = Calendar.getInstance();
        file = new File(this.getFilesDir(), VegetableBasketActivity.fileName);

        Vegetable v = new Vegetable("apple", calendar.getTime(), calendar.getTime(), false );
        Log.i("TAG", v.toString());
        
        ImageButton imageButton4 = findViewById(R.id.vegetable_basket_button);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked");
                startActivity(new Intent(MainActivity.this, VegetableBasketActivity.class));
            }
        });

//        Date date = new Date(); // This object contains the current date value
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//        String today = formatter.format(date);
//        String [] dateArray = today.split("-");
//        int day =  Integer.parseInt(dateArray[0]);
//        int month =  Integer.parseInt(dateArray[1]);
//        int year =  Integer.parseInt(dateArray[2]);
//        Log.i("TAG", today);

        readProduceData();

        button = findViewById(R.id.imageButton3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewItemActivity();

            }
        });

        imgButton = findViewById(R.id.image_button);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageActivity();
            }
        });
    }
    public void openNewItemActivity() {
        Intent intent = new Intent(this, NewItemActivity.class);
        intent.putParcelableArrayListExtra(PRODUCE_SAMPLE_CODE, produceSamples);
        startActivity(intent);
        this.finish();
    }

    public void openImageActivity() {
        Intent intent = new Intent(MainActivity.this, ImageActivity.class);
        startActivity(intent);
    }


    private void readProduceData() {
        InputStream is = getResources().openRawResource(R.raw.producedata);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF8"))
        );

        String line = "";
        try {
            while ((line = reader.readLine())!=null){
                String[] tokens = line.split(",");
                ProduceSample sample = new ProduceSample();
                sample.setProduce(tokens[0]);
                sample.setDays(Integer.parseInt(tokens[1]));
                produceSamples.add(sample);

                Log.d("MyActivity","Just Created: "+sample);
            }
        } catch (IOException e) {
            Log.wtf("MyActivity","Error reading data file on line" + line, e);
            e.printStackTrace();
        }
    }

    private void pickImageFromGallery () {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, IMAGE_PICK_CODE);
        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
                Intent intent = new Intent(this, ConfirmActivity.class);
                intent.putExtra(URI_CODE, data.getData().toString());
                startActivity(intent);


            }

        }

        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            switch (requestCode) {
                case PERMISSION_CODE:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //permission granted
                        pickImageFromGallery();
                    } else {
                        Toast.makeText(this, "Permission denied lol wut did u expect", Toast.LENGTH_LONG);
                    }
            }
        }

}

