package com.example.group3.firststepsapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class FeedbackView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_view);

        ImageButton exitButton = (ImageButton) findViewById(R.id.imageButton);
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

        Button saveButton = (Button) findViewById(R.id.button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Toast.makeText(getBaseContext(), "Feedback Recorded!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

        });

    }

}
