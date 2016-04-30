package com.example.group3.firststepsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.geofire.GeoFire;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SavedMeetings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SavedMeetings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedMeetings extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private LinearLayout listView;

    private Firebase myFirebaseRef;
    private Firebase geoFireRef;
    private Firebase meetings;
    private DBHelper mydb;

    private HashMap<String, String> listMeetings = new HashMap<String, String>();

    private OnFragmentInteractionListener mListener;

    public SavedMeetings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SavedMeetings.
     */
    // TODO: Rename and change types and number of parameters
    public static SavedMeetings newInstance(String param1, String param2) {
        SavedMeetings fragment = new SavedMeetings();
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
        View view = inflater.inflate(R.layout.fragment_saved_meetings, container, false);
        listView = (LinearLayout) view.findViewById(R.id.saved_list);

        myFirebaseRef = new Firebase("https://first-steps.firebaseio.com/");
        meetings = myFirebaseRef.child("meetings");
        mydb = new DBHelper(getContext());

        ArrayList<Integer> saved_meetings = mydb.getAllSavedMeetings();

        for (int i = 0; i < saved_meetings.size(); i++) {
            final String key = saved_meetings.get(i) + "";
            Firebase specificMeeting = meetings.child(key);
            specificMeeting.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Meeting meeting = dataSnapshot.getValue(Meeting.class);
                    View adapter = getActivity().getLayoutInflater().inflate(R.layout.fragment_list_view_adapter, null);
                    TextView nameView = (TextView) adapter.findViewById(R.id.textView);
                    TextView addressView = (TextView) adapter.findViewById(R.id.textView2);
                    TextView timeView = (TextView) adapter.findViewById(R.id.textView3);
                    TextView distanceView = (TextView) adapter.findViewById(R.id.textView4);

                    nameView.setText(meeting.getName());
                    addressView.setText(meeting.getAddress());
                    timeView.setText(meeting.getTime());
                    distanceView.setText("");

                    listMeetings.put(adapter.hashCode() + "", key);

                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String key = listMeetings.get(v.hashCode() + "");
                            Intent detailIntent = new Intent(getContext(), DetailView.class);
                            detailIntent.putExtra("key", key);
                            startActivity(detailIntent);
                        }
                    });

                    listView.addView(adapter);
                    View v = new View(getContext());
                    v.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            5
                    ));
                    v.setBackgroundColor(Color.parseColor("#D0D0D0"));
                    listView.addView(v);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }


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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
