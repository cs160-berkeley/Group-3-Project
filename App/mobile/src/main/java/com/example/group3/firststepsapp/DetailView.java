package com.example.group3.firststepsapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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

public class DetailView extends Activity {

    Firebase myFirebaseRef;
    private DBHelper mydb;

    private Meeting currentMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        myFirebaseRef = new Firebase("https://first-steps.firebaseio.com/");
        Firebase geoFireRef = myFirebaseRef.child("geofire");
        Firebase meetings = myFirebaseRef.child("meetings");
        final String key = getIntent().getExtras().getString("key");
        final Firebase specificMeeting = meetings.child(key);
        final Firebase geoFireRefSpecific = geoFireRef.child(key);

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
                currentMeeting = meeting;
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
                if (!meeting.getNumberOfAttendees().equalsIgnoreCase("Not Enough Feedback")) {
                    numberOfAttendeesView.setText(meeting.getNumberOfAttendees() + " Attendees");
                }
                else {
                    numberOfAttendeesView.setText(meeting.getNumberOfAttendees());
                }
                if (!meeting.getAverageAge().equalsIgnoreCase("Not Enough Feedback")) {
                    averageAgeView.setText(meeting.getAverageAge() + " Years Old");
                }
                else {
                    averageAgeView.setText(meeting.getAverageAge());
                }
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
                        mydb.insertContact(Integer.parseInt(key), currentMeeting.getName(), currentMeeting.getAddress(), currentMeeting.getTime(),
                                currentMeeting.getAverageAge(), currentMeeting.getNumberOfAttendees());
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
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        specificMeeting.removeValue();
                                        geoFireRefSpecific.removeValue();
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        dialog.dismiss();
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(DetailView.this, R.style.MyAlertDialogStyle);
                        TextView titleView = (TextView) findViewById(R.id.textView9);
                        String nameMeeting = titleView.getText().toString();
                        builder.setTitle("Delete " + nameMeeting).setMessage("Are you sure? This will delete " + nameMeeting + " permanently.").setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }

}
