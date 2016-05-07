package com.example.group3.firststepsapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class FeedbackView extends AppCompatActivity {

    private ArrayList<ToggleButton> ageToggles = new ArrayList<ToggleButton>();
    private ArrayList<ToggleButton> numPeopleToggles = new ArrayList<ToggleButton>();
    private ArrayList<ToggleButton> descriptionsToggle = new ArrayList<ToggleButton>();

    private String key;

    private Firebase myFirebaseRef;
    private Firebase meetings;
    private Firebase specificMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_view);

        String name = getIntent().getStringExtra("name");
        key = getIntent().getStringExtra("key");
        System.out.println(key);

        myFirebaseRef = new Firebase("https://first-steps.firebaseio.com/");
        meetings = myFirebaseRef.child("meetings");
        specificMeeting = meetings.child(key);

        TextView titleView = (TextView) findViewById(R.id.textView9);
        titleView.setText(name + " Feedback");

        ToggleButton under30 = (ToggleButton) findViewById(R.id.toggleButton0);
        ageToggles.add(under30);
        ToggleButton middleAge = (ToggleButton) findViewById(R.id.toggleButton1);
        ageToggles.add(middleAge);
        ToggleButton over60 = (ToggleButton) findViewById(R.id.toggleButton2);
        ageToggles.add(over60);

        ToggleButton under25 = (ToggleButton) findViewById(R.id.toggleButton3);
        numPeopleToggles.add(under25);
        ToggleButton middlePeople = (ToggleButton) findViewById(R.id.toggleButton4);
        numPeopleToggles.add(middlePeople);
        ToggleButton over75 = (ToggleButton) findViewById(R.id.toggleButton5);
        numPeopleToggles.add(over75);

        ToggleButton engaging = (ToggleButton) findViewById(R.id.toggleButton6);
        descriptionsToggle.add(engaging);
        ToggleButton respectful = (ToggleButton) findViewById(R.id.toggleButton7);
        descriptionsToggle.add(respectful);
        ToggleButton spacious = (ToggleButton) findViewById(R.id.toggleButton8);
        descriptionsToggle.add(spacious);
        ToggleButton organized = (ToggleButton) findViewById(R.id.toggleButton9);
        descriptionsToggle.add(organized);
        ToggleButton energetic = (ToggleButton) findViewById(R.id.toggleButton10);
        descriptionsToggle.add(energetic);
        ToggleButton enthusiastic = (ToggleButton) findViewById(R.id.toggleButton11);
        descriptionsToggle.add(enthusiastic);
        ToggleButton resourceful = (ToggleButton) findViewById(R.id.toggleButton12);
        descriptionsToggle.add(resourceful);
        ToggleButton supportive = (ToggleButton) findViewById(R.id.toggleButton13);
        descriptionsToggle.add(supportive);
        ToggleButton helpful = (ToggleButton) findViewById(R.id.toggleButton14);
        descriptionsToggle.add(helpful);

        for (int i = 0; i < ageToggles.size(); i++) {
            final int currenti = i;
            ageToggles.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (currenti == 0) {
                        ageToggles.get(currenti).setChecked(true);
                        ageToggles.get(currenti + 1).setChecked(false);
                        ageToggles.get(currenti + 2).setChecked(false);
                    }
                    else if (currenti == 1) {
                        ageToggles.get(currenti).setChecked(true);
                        ageToggles.get(currenti - 1).setChecked(false);
                        ageToggles.get(currenti + 1).setChecked(false);
                    }
                    else if (currenti == 2) {
                        ageToggles.get(currenti).setChecked(true);
                        ageToggles.get(currenti - 1).setChecked(false);
                        ageToggles.get(currenti - 2).setChecked(false);
                    }
                }
            });
        }

        for (int i = 0; i < numPeopleToggles.size(); i++) {
            final int currenti = i;
            numPeopleToggles.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (currenti == 0) {
                        numPeopleToggles.get(currenti).setChecked(true);
                        numPeopleToggles.get(currenti + 1).setChecked(false);
                        numPeopleToggles.get(currenti + 2).setChecked(false);
                    }
                    else if (currenti == 1) {
                        numPeopleToggles.get(currenti).setChecked(true);
                        numPeopleToggles.get(currenti - 1).setChecked(false);
                        numPeopleToggles.get(currenti + 1).setChecked(false);
                    }
                    else if (currenti == 2) {
                        numPeopleToggles.get(currenti).setChecked(true);
                        numPeopleToggles.get(currenti - 1).setChecked(false);
                        numPeopleToggles.get(currenti - 2).setChecked(false);
                    }
                }
            });
        }

        ImageButton exitButton = (ImageButton) findViewById(R.id.imageButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        Button saveButton = (Button) findViewById(R.id.button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Firebase ageRef = specificMeeting.child("averageAge");
                    for (int i = 0; i < ageToggles.size(); i++) {
                        ToggleButton ageButton = ageToggles.get(i);
                        if (ageButton.isChecked()) {
                            final Firebase ageRangeRef = ageRef.child(ageButton.getTextOn().toString());
                            System.out.println(ageButton.getTextOn().toString());
                            ageRangeRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    System.out.println(snapshot.getValue());
                                    long numVoted = (long) snapshot.getValue();
                                    ageRangeRef.setValue(numVoted + 1);
                                }
                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                }
                            });
                        }
                    }


                    Firebase peopleRef = specificMeeting.child("numberOfAttendees");
                    for (int i = 0; i < numPeopleToggles.size(); i++) {
                        ToggleButton peopleButton = numPeopleToggles.get(i);
                        if (peopleButton.isChecked()) {
                            final Firebase peopleRangeRef = peopleRef.child(peopleButton.getTextOn().toString());
                            peopleRangeRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    long numVoted = (long) snapshot.getValue();
                                    peopleRangeRef.setValue(numVoted + 1);
                                }
                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                }
                            });
                        }
                    }

                    Firebase descriptionRef = specificMeeting.child("descriptions");
                    for (int i = 0; i < descriptionsToggle.size(); i++) {
                        ToggleButton descriptionButton = descriptionsToggle.get(i);
                        if (descriptionButton.isChecked()) {
                            final Firebase descriptionsRef = descriptionRef.child(descriptionButton.getTextOn().toString());
                            descriptionsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    long numVoted = (long) snapshot.getValue();
                                    descriptionsRef.setValue(numVoted + 1);
                                }
                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                }
                            });
                        }
                    }

                    Toast.makeText(getBaseContext(), "Feedback Recorded!", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }

}
