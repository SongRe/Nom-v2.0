package com.example.nomv20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfirmActivity extends AppCompatActivity {

    ArrayList<String> rawText = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        Intent intent = getIntent();
        String stringUri = intent.getStringExtra(MainActivity.URI_CODE);
        Uri imgUri = Uri.parse(stringUri);

        InputImage inputImage = null;
        try {
            inputImage = InputImage.fromFilePath(this, imgUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        recognizeText(inputImage);
        for (int i = 0; i < rawText.size(); i++) {
            Log.v("test", rawText.get(i));
        }


    }

    public void recognizeText(InputImage inputImage) {
        InputImage image = inputImage;
        TextRecognizer recognizer = TextRecognition.getClient();
        recognizer.process(image)
                .addOnSuccessListener(
                        new OnSuccessListener<Text>() {
                            @Override
                            public void onSuccess(Text texts) {

                                processTextRecognitionResult(texts);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception

                                e.printStackTrace();
                            }
                        });


    }

    private void processTextRecognitionResult(Text texts) {
        List<Text.TextBlock> blocks = texts.getTextBlocks();
        if (blocks.size() == 0) {
            return;
        }
        for (int i = 0; i < blocks.size(); i++) {
            Text.TextBlock b = blocks.get(i);
            rawText.add(b.getText());
        }
    }

}