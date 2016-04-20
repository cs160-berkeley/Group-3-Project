package com.example.group3.firststepsapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MapView.OnFragmentInteractionListener, SavedMeetings.OnFragmentInteractionListener, NewMeetingFrag.OnFragmentInteractionListener,
                    AALiteratureFrag.OnFragmentInteractionListener, DailyReflectionFrag.OnFragmentInteractionListener, ListFrag.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        android.support.v4.app.FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content_frame, new MapView());
        tx.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.map) {
            android.support.v4.app.FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.content_frame, new MapView());
            tx.commit();
            // set the toolbar title
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("First Steps");
            }
            return true;
        } else if (id == R.id.list) {
            android.support.v4.app.FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.content_frame, new ListFrag());
            tx.commit();
            // set the toolbar title
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("First Steps");
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here

        android.support.v4.app.FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        String title = "";

        switch(item.getItemId()) {
            case R.id.nav_explore:
                tx.replace(R.id.content_frame, new MapView());
                tx.commit();
                title = "First Steps";
                break;
            case R.id.nav_saved:
                tx.replace(R.id.content_frame, new SavedMeetings());
                tx.commit();
                title = "Saved Meetings";
                break;
            case R.id.nav_add:
                tx.replace(R.id.content_frame, new NewMeetingFrag());
                tx.commit();
                title = "Add New Meetings";
                break;
            case R.id.nav_literature:
                tx.replace(R.id.content_frame, new AALiteratureFrag());
                tx.commit();
                title = "AA Literature";
                break;
            case R.id.nav_reflection:
                tx.replace(R.id.content_frame, new DailyReflectionFrag());
                tx.commit();
                title = "Daily Reflection";
                break;
            default:
                tx.replace(R.id.content_frame, new MapView());
                tx.commit();
                break;
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

//        if (id == R.id.nav_explore) {
//            // Handle the camera action
//        } else if (id == R.id.nav_add) {
//
//        } else if (id == R.id.nav_saved) {
//
//        } else if (id == R.id.nav_literature) {
//
//        } else if (id == R.id.nav_reflection) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        System.out.println("here");
    }
}
