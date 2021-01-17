package com.example.nomv20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class VegetableBasketActivity extends AppCompatActivity {
    public File file;
    public static final String fileName = "currentVegetables.txt";

    private LinearLayout sLinearLayout;
    private CardView[] cardViews;
    private ArrayList<Vegetable> vegetables = new ArrayList<>();
    Calendar calendar;

    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable_basket);
        file = new File(this.getFilesDir(), fileName);
        currentVegArray();
        calendar = Calendar.getInstance();
       // updateExpiry();

        sLinearLayout = findViewById(R.id.viewList);


        params.setMargins(0, 10, 0, 40);
        populateCardViews(); //fill the cardview array
        populateLayout();   //fill the layout with cardviews


    }

    private void updateExpiry() {
        Date curDate = calendar.getTime();
        for (Vegetable v : vegetables
        ) {
            Date expDate = v.getExpirationDate();
            if(expDate.before(curDate));
            v.setExpired(true);

        }
    }

    private void populateLayout() {

        for (CardView cardView : cardViews) {
            try {
                sLinearLayout.addView(cardView);
            } catch (Exception e) {

            }

        }
    }

    private void populateCardViews() {
//        LinearLayout.LayoutParams imageParams;
//        imageParams = new LinearLayout.LayoutParams(200,200);
//        imageParams.setMarginStart(10);
//
//
//        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.MATCH_PARENT
//        );
//        relativeParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        cardViews = new CardView[vegetables.size()];
        for (int i = 0; i < vegetables.size(); i++) {
            Vegetable currentVegetable = vegetables.get(i);
            if (!currentVegetable.getExpired()) {
                Date currentDate = calendar.getTime();
                Date expiryDate = currentVegetable.getExpirationDate();
                long difference = expiryDate.getTime() - currentDate.getTime();
                long difference_in_days = (difference / (1000 * 60 * 60 * 24)) % 365;

                // Initialize new TextView to put in CardView

                TextView tv = new TextView(this);
                tv.setLayoutParams(params);
                tv.setText(vegetables.get(i).getName().toUpperCase() + " With " + difference_in_days + " days left");
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                tv.setTextColor(Color.WHITE);
                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                //adding text and image to view
//            layout.addView(iv);
//            layout.addView(tv);

                CardView card = new CardView(this);
                card.setLayoutParams(params);
                // Set CardView corner radius
                card.setRadius(20);
                // Set cardView content padding
                card.setContentPadding(15, 15, 15, 15);
                // Set a background color for CardView
                card.setCardBackgroundColor(getColor(R.color.colorPrimary));
                // Set the CardView maximum elevation
                card.setMaxCardElevation(30);
                // Set CardView elevation
                card.setCardElevation(30);
                // card.addView(layout);
                card.addView(tv);
                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                cardViews[i] = card;
            }
            //   RelativeLayout layout  = new RelativeLayout(this);

//            ImageView iv = new ImageView(this);
//            String res = vegetables.get(i).getName();
//            iv.setLayoutParams(imageParams);
//            int resID = this.getResources().getIdentifier(res, "drawable", getPackageName());
//            iv.setBackgroundResource(resID);

        }


    }

    public void currentVegArray() {

        try {
            Scanner fileIn = new Scanner(file);
            String s;
            while (fileIn.hasNextLine()) {
                s = fileIn.nextLine();
                String[] split = s.split(",");
//                boolean expired = Boolean.parseBoolean(split[0]);
                DateFormat format = new SimpleDateFormat("EEE MMMM d HH:mm:ss z yyyy", Locale.ENGLISH);

                Date expDate = format.parse(split[2]);
                Date entDate = format.parse(split[3]);

                Vegetable v = new Vegetable(split[1], expDate, entDate, Boolean.parseBoolean(split[0]));
                vegetables.add(v);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}