package com.example.group3.firststepsapp;

/**
 * Created by Simon on 4/30/16.
 */
public class MeetingDB {
    private String key;
    private String name;
    private String address;
    private String time;
    private String age;
    private String numpeople;

    public MeetingDB(String key, String name, String address, String time, String age, String numpeople) {
        this.key = key;
        this.name = name;
        this.address = address;
        this.time = time;
        this.age = age;
        this.numpeople = numpeople;
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
    public String getAge() {
        return age;
    }
    public String getNumPeople() {
        return numpeople;
    }
    public String getKey() {
        return key;
    }
}
