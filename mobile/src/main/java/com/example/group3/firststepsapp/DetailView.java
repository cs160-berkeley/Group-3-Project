package com.example.group3.firststepsapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class DetailView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        ImageButton exitButton = (ImageButton) findViewById(R.id.imageButton2);
        exitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    finish();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

        });

        ImageButton feedBackButton = (ImageButton) findViewById(R.id.imageButton3);
        feedBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Intent feedbackIntent = new Intent(getBaseContext(), FeedbackView.class);
                    startActivity(feedbackIntent);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button existButton = (Button) findViewById(R.id.button2);
        existButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Toast.makeText(getBaseContext(), "Doesn't Exist Recorded", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
