package com.example.nomv20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;

import com.google.mlkit.vision.common.InputImage;

import java.io.IOException;

public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        Intent intent = getIntent();
        String stringUri = intent.getStringExtra(MainActivity.URI_CODE);
        Uri imgUri = Uri.parse(stringUri);

        InputImage inputimage;
        try{
            inputimage = InputImage.fromFilePath(this, imgUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}