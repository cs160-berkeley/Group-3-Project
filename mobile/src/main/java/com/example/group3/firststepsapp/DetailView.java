package com.example.group3.firststepsapp;

import android.content.Intent;
import android.database.Cursor;
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
import android.widget.ToggleButton;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailView extends AppCompatActivity {

    Firebase myFirebaseRef;
    private DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        myFirebaseRef = new Firebase("https://first-steps.firebaseio.com/");
        Firebase meetings = myFirebaseRef.child("meetings");
        final String key = getIntent().getExtras().getString("key");
        Firebase specificMeeting = meetings.child(key);

        mydb = new DBHelper(this);

        Cursor rs = mydb.getData(Integer.parseInt(key));
        final boolean saved;
        if (rs.moveToFirst()) {
            System.out.println("is saved");
            saved = true;
        }
        else {
            System.out.println("not saved");
            saved = false;
        }



        specificMeeting.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Meeting meeting = dataSnapshot.getValue(Meeting.class);
                TextView addressView = (TextView) findViewById(R.id.textView5);
                TextView timeView = (TextView) findViewById(R.id.textView6);
                TextView averageAgeView = (TextView) findViewById(R.id.textView7);
                TextView numberOfAttendeesView = (TextView) findViewById(R.id.textView8);
                TextView titleView = (TextView) findViewById(R.id.textView9);
                TextView descriptionView = (TextView) findViewById(R.id.textView13);
                ToggleButton savedView = (ToggleButton) findViewById(R.id.toggleButton);

                titleView.setText(meeting.getName());
                addressView.setText(meeting.getAddress());
                timeView.setText(meeting.getTime());
                numberOfAttendeesView.setText(meeting.getNumberOfAttendees() + " Attendees");
                averageAgeView.setText(meeting.getAverageAge() + " Years Old");
                descriptionView.setText("The meeting is " + meeting.getDescriptions());

                if (saved) {
                    savedView.setChecked(true);
                }
            }

                @Override
                public void onCancelled (FirebaseError firebaseError){

                }
            }

            );
            ToggleButton savedView = (ToggleButton) findViewById(R.id.toggleButton);

            savedView.setOnClickListener(new View.OnClickListener() {
                public void onClick (View v) {
                    ToggleButton savedView = (ToggleButton) findViewById(R.id.toggleButton);

                    // this is counter intuitive, something about onclick being triggered before setchecked, idk
                    if (!savedView.isChecked()) {
                        mydb.deleteContact(Integer.parseInt(key));
                        savedView.setChecked(false);
                    }
                    else {
                        mydb.insertContact(Integer.parseInt(key));
                        savedView.setChecked(true);
                    }
                }
            });

            ImageButton exitButton = (ImageButton) findViewById(R.id.imageButton2);
            exitButton.setOnClickListener(new View.OnClickListener()

            {
                public void onClick (View v){
                try {
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            }

            );

            ImageButton feedBackButton = (ImageButton) findViewById(R.id.imageButton3);
            feedBackButton.setOnClickListener(new View.OnClickListener()

              {
                  public void onClick(View v) {
                      try {
                          Intent feedbackIntent = new Intent(getBaseContext(), FeedbackView.class);
                          feedbackIntent.putExtra("key", getIntent().getExtras().getString("key"));
                          TextView titleView = (TextView) findViewById(R.id.textView9);
                          feedbackIntent.putExtra("name",titleView.getText().toString());
                          startActivity(feedbackIntent);
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                  }
              }

            );

            Button existButton = (Button) findViewById(R.id.button2);
            existButton.setOnClickListener(new View.OnClickListener()

            {
                public void onClick(View v) {
                    try {
                        Toast.makeText(getBaseContext(), "Doesn't Exist Recorded", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }

}
