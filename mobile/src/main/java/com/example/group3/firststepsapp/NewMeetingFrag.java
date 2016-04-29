package com.example.group3.firststepsapp;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.vision.barcode.Barcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewMeetingFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewMeetingFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewMeetingFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Firebase myFirebaseRef;
    private Firebase geoFireRef;
    private Firebase meetings;
    private GeoFire geoFire;
    private Firebase numberOfMeetings;

    private long numMeetings;

    private OnFragmentInteractionListener mListener;

    public NewMeetingFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewMeetingFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static NewMeetingFrag newInstance(String param1, String param2) {
        NewMeetingFrag fragment = new NewMeetingFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myFirebaseRef = new Firebase("https://first-steps.firebaseio.com/");
        geoFireRef = myFirebaseRef.child("geofire");
        meetings = myFirebaseRef.child("meetings");
        geoFire = new GeoFire(geoFireRef);
        numberOfMeetings = myFirebaseRef.child("numberOfMeetings");

        View view = inflater.inflate(R.layout.fragment_new_meeting, container, false);
        Button detailButton = (Button) view.findViewById(R.id.button);
        final EditText nameView = (EditText) view.findViewById(R.id.editText);
        final EditText timeView = (EditText) view.findViewById(R.id.editText2);
        final EditText addressView = (EditText) view.findViewById(R.id.editText3);
        final EditText ageView = (EditText) view.findViewById(R.id.editText4);
        final EditText groupView = (EditText) view.findViewById(R.id.editText5);

        detailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {

                    final String name = nameView.getText().toString();
                    final String time = timeView.getText().toString();
                    final String address = addressView.getText().toString();
                    String age = ageView.getText().toString();
                    String group = groupView.getText().toString();

                    if (age.equalsIgnoreCase("")) {
                        age = "Unknown";
                    }
                    if (group.equalsIgnoreCase("")) {
                        group = "Unknown";
                    }
                    if (name.equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (time.equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Please Enter Time", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (address.equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Please Enter Address", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    final String finalAge = age;
                    final String finalGroup = group;

                    final LatLng currentLatLng = getLatLngFromAddress(address);

                    numberOfMeetings.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            numMeetings = (long) snapshot.getValue();
                            long nextMeetingNum = numMeetings + 1;
                            Firebase newMeetingRef = meetings.child(nextMeetingNum + "");
                            newMeetingRef.child("name").setValue(name);
                            newMeetingRef.child("address").setValue(address);
                            newMeetingRef.child("time").setValue(time);
                            newMeetingRef.child("averageAge").setValue(finalAge);
                            newMeetingRef.child("numberOfAttendees").setValue(finalGroup);

                            geoFire.setLocation(nextMeetingNum + "", new GeoLocation(currentLatLng.latitude, currentLatLng.longitude));

                            numberOfMeetings.setValue(nextMeetingNum);

                            Toast.makeText(getContext(), "Meeting Saved!", Toast.LENGTH_SHORT).show();
                            android.support.v4.app.FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
                            tx.replace(R.id.content_frame, new MapView());
                            tx.commit();
                            String title = "First Steps";
                            // set the toolbar title
                            if (((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
                                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
                            }


                            DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                            NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                            navigationView.getMenu().getItem(0).setChecked(true);
                            drawer.closeDrawer(GravityCompat.START);
                        }
                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                        }
                    });
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public LatLng getLatLngFromAddress(String address) {
        Geocoder coder = new Geocoder(getContext());
        LatLng newLatLng = null;
        try {
            List<Address> addresses = coder.getFromLocationName(address, 1);
            Address newAddress = addresses.get(0);
            newLatLng = new LatLng(newAddress.getLatitude(), newAddress.getLongitude());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newLatLng;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
