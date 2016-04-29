package com.example.group3.firststepsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.geofire.GeoFire;

import java.util.ArrayList;


public class ListViewAdapter extends BaseAdapter {

    private ArrayList<String> name;
    private ArrayList<String> address;
    private ArrayList<String> time;
    private ArrayList<Double> distance;
    private ArrayList<String> favorite;
    private Context context;

    public ListViewAdapter(Context context, ArrayList<String> name, ArrayList<String> address, ArrayList<String> time, ArrayList<Double> distance, ArrayList<String> favorite) {
        this.context = context;
        this.name = name;
        this.address = address;
        this.time = time;
        this.distance = distance;
        this.favorite = favorite;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_list_view_adapter, parent, false);
        TextView nameView = (TextView) view.findViewById(R.id.textView);
        TextView addressView = (TextView) view.findViewById(R.id.textView2);
        TextView timeView = (TextView) view.findViewById(R.id.textView3);
        TextView distanceView = (TextView) view.findViewById(R.id.textView4);

        nameView.setText(name.get(position));
        addressView.setText(address.get(position));
        timeView.setText(time.get(position));
        distanceView.setText(distance.get(position) + "");

        return view;
    }

    public int getCount() {
        return 3;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

}