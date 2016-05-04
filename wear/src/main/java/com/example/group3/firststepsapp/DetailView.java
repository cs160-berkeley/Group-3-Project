package com.example.group3.firststepsapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DetailView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        ImageButton directionsButton = (ImageButton) findViewById(R.id.imageButton3);
        directionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                sendIntent.putExtra("extra", "directions");
                sendIntent.putExtra("uri", "geo:0,0?q=2600 Bancroft Way,Berkeley,CA 94704");
                startService(sendIntent);
            }
        });
    }

}
