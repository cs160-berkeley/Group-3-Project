package com.example.group3.firststepsapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class DetailView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        ImageButton exitIntent = (ImageButton) findViewById(R.id.imageButton2);
        exitIntent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    finish();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

        });
    }

}
