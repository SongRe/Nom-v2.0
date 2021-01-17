package com.example.nomv20;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.TextView;
import android.net.Uri;

import com.google.android.gms.tasks.OnFailureListener;

import java.util.Calendar;

public class ImageActivity extends BaseActivity implements View.OnClickListener {

    private Bitmap myBitmap;
    private ImageView myImageView;
    private TextView myTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        myTextView = findViewById(R.id.textView);
        myImageView = findViewById(R.id.imageView);
        findViewById(R.id.checkText).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkText:
                if (myBitmap != null) {
                    //runTextRecog();
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case WRITE_STORAGE:
                    checkPermission(requestCode);
                    break;
                case SELECT_PHOTO:
                    Uri dataUri = data.getData();
                    String path = MyHelper.getPath(this, dataUri);
                    if (path == null) {
                        myBitmap = MyHelper.resizePhoto(photo, this, dataUri, myImageView);
                    } else {
                        myBitmap = MyHelper.resizePhoto(photo, path, myImageView);
                    }
                    if (myBitmap != null) {
                        myTextView.setText(null);
                        myImageView.setImageBitmap(myBitmap);
                    }
                    break;

            }
        }
    }

//    private <FirebaseVisionImage> void runTextRecog() {
//        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(myBitmap);
//        FirebaseVisionTextDetector detector = FirebaseVision.getInstance().getVisionTextDetector();
//        detector.detectInImage(image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
//            @Override
//            public void onSuccess(FirebaseVisionText texts) {
//                processExtractedText(texts);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure
//                    (@NonNull Exception exception) {
//                Toast.makeText(ImageActivity.this,
//                        "Exception", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    private void processExtractedText(FirebaseVisionText firebaseVisionText) {
//        myTextView.setText(null);
//        if (firebaseVisionText.getBlocks().size() == 0) {
//            myTextView.setText(R.string.no_text);
//            return;
//        }
//        for (FirebaseVisionText.Block block : firebaseVisionText.getBlocks()) {
//            myTextView.append(block.getText());
//
//        }
//    }

}