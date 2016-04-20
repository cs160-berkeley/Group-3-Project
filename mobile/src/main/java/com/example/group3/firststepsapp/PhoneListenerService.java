package com.example.group3.firststepsapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
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
            sendIntent.putExtra("extra", "location");
            startService(sendIntent);
        }
        else if (message.equalsIgnoreCase("meetings")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {

                @Override
                public void run() {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, "Fetching All Saved Meetings", duration);
                    toast.show();
                }
            });

            Context context = getApplicationContext();
            Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
            sendIntent.putExtra("extra", "meetings");
            startService(sendIntent);
        }
        else if (message.equalsIgnoreCase("directions")) {
            // create a handler to post messages to the main thread
//            Handler handler = new Handler(Looper.getMainLooper());
//            handler.post(new Runnable() {
//
//                @Override
//                public void run() {
//                    Context context = getApplicationContext();
//                    int duration = Toast.LENGTH_SHORT;
//
//                    Toast toast = Toast.makeText(context, "Get Directions From Google Maps", duration);
//                    toast.show();
//                }
//            });

            String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 37.85, -122.25);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
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

        else if (message.equalsIgnoreCase("alert")) {
            // create a handler to post messages to the main thread
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {

                @Override
                public void run() {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, "Toggle Alert", duration);
                    toast.show();
                }
            });
        }


    }
}
