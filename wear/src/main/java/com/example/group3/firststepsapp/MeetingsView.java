package com.example.group3.firststepsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.MotionEventCompat;
import android.support.wearable.view.GridViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MeetingsView extends Activity implements SaveMeetingsFrag.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings_view);

        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
        GridPagerAdapter gridpager = new GridPagerAdapter(this, getFragmentManager());
        gridpager.setData(getIntent().getStringExtra("meetings"));
        gridpager.makeData();
        pager.setAdapter(gridpager);

    }

}
