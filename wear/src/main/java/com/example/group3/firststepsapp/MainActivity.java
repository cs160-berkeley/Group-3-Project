package com.example.group3.firststepsapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton nearestMeeting = (ImageButton) findViewById(R.id.imageButton2);
        nearestMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//                sendIntent.putExtra("extra", "location");
//                startService(sendIntent);
                Intent mapIntent = new Intent(getBaseContext(), MapView.class);
                startActivity(mapIntent);
            }
        });

        ImageButton myMeetings = (ImageButton) findViewById(R.id.imageButton);;
        myMeetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                sendIntent.putExtra("extra", "meetings");
                startService(sendIntent);
            }
        });
    }

}
