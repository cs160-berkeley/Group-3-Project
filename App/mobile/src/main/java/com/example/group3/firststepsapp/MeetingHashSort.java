package com.example.group3.firststepsapp;

import java.util.Comparator;

/**
 * Created by Simon on 4/29/16.
 */
public class MeetingHashSort {

    private String hash;
    private double distance;

    public MeetingHashSort(String hash, double distance) {
        super();
        this.hash = hash;
        this.distance = distance;
    }

    public String getHash() {
        return this.hash;
    }

    public double getDistance() {
        return this.distance;
    }

    public int compareTo(MeetingHashSort compareMeeting) {

        double compareQuantity = ((MeetingHashSort) compareMeeting).getDistance();

        //ascending order
        return (int) ((this.distance - compareQuantity) * 10);

        //descending order
        //return compareQuantity - this.quantity;

    }

    public static Comparator<MeetingHashSort> FruitNameComparator
            = new Comparator<MeetingHashSort>() {

        public int compare(MeetingHashSort meeting1,MeetingHashSort meeting2) {


            //ascending order
            return meeting1.compareTo(meeting2);

            //descending order
            //return fruitName2.compareTo(fruitName1);
        }

    };
}
