package com.example.group3.firststepsapp;

/**
 * Created by Simon on 4/26/16.
 */
public class Meeting {
    private String name;
    private String address;
    private String time;
    private String meetingKey;
    private String numberOfAttendees;
    private String averageAge;

    public Meeting() {

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTime() {
        return time;
    }

    public void setKey(String key) {
        meetingKey = key;
    }

    public String getKey() {
        return meetingKey;
    }

    public String getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public String getAverageAge() {
        return averageAge;
    }
}
