package com.example.group3.firststepsapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.wearable.view.FragmentGridPagerAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 4/30/16.
 */
public class GridPagerAdapter extends FragmentGridPagerAdapter {

    private final Context mContext;
    private List mRows;
    private String data;
    private Page[][] PAGES;

    public GridPagerAdapter(Context ctx, android.app.FragmentManager fm) {
        super(fm);
        mContext = ctx;
    }

    // A simple container for static data in each page
    private static class Page {
        String name;
        String address;
        String time;
        String age;
        String numpeople;

        public Page(String name, String address, String time, String age, String numpeople) {
            this.name = name;
            this.address = address;
            this.time = time;
            this.age = age;
            this.numpeople = numpeople;
        }
    }


    // Obtain the UI fragment at the specified position
    @Override
    public android.app.Fragment getFragment(int row, int col) {
        Page page = PAGES[row][col];
        Bundle bundle = new Bundle();
        bundle.putString("name", page.name);
        bundle.putString("address", page.address);
        bundle.putString("time", page.time);
        bundle.putString("age", page.age);
        bundle.putString("numpeople", page.numpeople);
        android.app.Fragment meetingFrag = new SaveMeetingsFrag();
        meetingFrag.setArguments(bundle);
        return meetingFrag;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void makeData() {
        System.out.println("data" + this.data);
        if (this.data.length() != 0) {
            String[] meetings = data.split("/");
            PAGES = new Page[1][meetings.length];
            for (int i = 0; i < meetings.length; i++) {
                String meeting = meetings[i];
                System.out.println(meeting);
                String[] meetingDetails = meeting.split("\\|");
                System.out.println(meetingDetails[0]);
                System.out.println(meetingDetails[1]);
                System.out.println(meetingDetails[2]);
                Page meetingPage = new Page(meetingDetails[0], meetingDetails[1], meetingDetails[2], meetingDetails[3], meetingDetails[4]);
                PAGES[0][i] = meetingPage;
            }
        }
        else {
            PAGES = new Page[0][0];
        }
    }

    // Obtain the number of pages (vertical)
    @Override
    public int getRowCount() {
        return PAGES.length;
    }

    // Obtain the number of pages (horizontal)
    @Override
    public int getColumnCount(int rowNum) {
        return PAGES[rowNum].length;
    }
}
