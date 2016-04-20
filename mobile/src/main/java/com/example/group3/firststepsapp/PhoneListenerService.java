package com.example.group3.firststepsapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import android.os.Handler;

public class PhoneListenerService extends WearableListenerService {

    //   WearableListenerServices don't need an iBinder or an onStartCommand: they just need an onMessageReceieved.

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in PhoneListenerService, got: " + messageEvent.getPath());
        String message = messageEvent.getPath();
        if (Objects.equals(message, "location")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {

                @Override
                public void run() {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, "Finding Nearest Meetings", duration);
                    toast.show();
                }
            });

            Context context = getApplicationContext();
            Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
            sendIntent.putExtra("location", "location");
            startService(sendIntent);
        }
        else if (message.equalsIgnoreCase("directions")) {
            // create a handler to post messages to the main thread
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {

                @Override
                public void run() {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, "Get Directions From Google Maps", duration);
                    toast.show();
                }
            });
        }
        else if (message.equalsIgnoreCase("save")) {
            // create a handler to post messages to the main thread
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {

                @Override
                public void run() {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, "Saved Meeting", duration);
                    toast.show();
                }
            });
        }

//        System.out.println("GOT MESSAGE");
//
//            // Value contains the String we sent over in WatchToPhoneService, "good job"
//            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//
//            // Make a toast with the String
//            Context context = getApplicationContext();
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast = Toast.makeText(context, value, duration);
//            toast.show();
//
//            // so you may notice this crashes the phone because it's
//            //''sending message to a Handler on a dead thread''... that's okay. but don't do this.
//            // replace sending a toast with, like, starting a new activity or something.
//            // who said skeleton code is untouchable? #breakCSconceptions

    }
}
