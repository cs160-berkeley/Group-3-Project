package com.example.group3.firststepsapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Simon on 4/30/16.
 */
public class FirstStepsInfoAdapter implements GoogleMap.InfoWindowAdapter {

    LayoutInflater inflater=null;

    FirstStepsInfoAdapter(LayoutInflater inflater) {
        this.inflater=inflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View popup=inflater.inflate(R.layout.info_adapter, null);

        TextView tv=(TextView)popup.findViewById(R.id.textView14);

        tv.setText(marker.getTitle());
        String snippet = marker.getSnippet();
        String[] snippets = snippet.split("\n", 3);
        tv = (TextView) popup.findViewById(R.id.textView15);
        tv.setText(snippets[0]);
        tv = (TextView) popup.findViewById(R.id.textView16);
        if (!snippets[1].equalsIgnoreCase("Not Enough Feedback")) {
            tv.setText("Average Age Range: " + snippets[1]);
        }
        else {
            tv.setText("Average Age Range: Unknown");
        }
        tv = (TextView) popup.findViewById(R.id.textView17);
        if (!snippets[2].equalsIgnoreCase("Not Enough Feedback")) {
            tv.setText("Average # of Attendees: " + snippets[2]);
        }
        else {
            tv.setText("Average # of Attendees: Unknown");
        }
        return popup;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
