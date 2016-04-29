package com.example.group3.firststepsapp;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

public class DetailView extends AppCompatActivity {

    Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        myFirebaseRef = new Firebase("https://first-steps.firebaseio.com/");
        Firebase meetings = myFirebaseRef.child("meetings");
        Firebase specificMeeting = meetings.child(getIntent().getExtras().getString("key"));

        specificMeeting.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Meeting meeting = dataSnapshot.getValue(Meeting.class);
                TextView addressView = (TextView) findViewById(R.id.textView5);
                TextView timeView = (TextView) findViewById(R.id.textView6);
                TextView numberOfAttendeesView = (TextView) findViewById(R.id.textView7);
                TextView averageAgeView = (TextView) findViewById(R.id.textView8);
                TextView titleView = (TextView) findViewById(R.id.textView9);

                titleView.setText(meeting.getName());
                addressView.setText(meeting.getAddress());
                timeView.setText(meeting.getTime());
                numberOfAttendeesView.setText(meeting.getNumberOfAttendees());
                averageAgeView.setText(meeting.getAverageAge());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

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
