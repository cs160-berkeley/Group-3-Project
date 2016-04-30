package com.example.group3.firststepsapp;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Simon on 4/26/16.
 */
public class Meeting {
    private String name;
    private String address;
    private String time;
    private String meetingKey;
    private HashMap<String, Integer> numberOfAttendees;
    private HashMap<String, Integer> averageAge;
    private HashMap<String, Integer> descriptions;

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
        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : numberOfAttendees.entrySet())
        {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue())
            {
                maxEntry = entry;
            }
        }

        if (maxEntry.getValue() == 0) {
            return "Not Enough Feedback";
        }

        return maxEntry.getKey();
    }

    public String getAverageAge() {

        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : averageAge.entrySet())
        {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue())
            {
                maxEntry = entry;
            }
        }

        if (maxEntry.getValue() == 0) {
            return "Not Enough Feedback";
        }

        return maxEntry.getKey();
    }

    public String getDescriptions() {
        Comparator<Descriptions> comparator = new ValueComparator();
        PriorityQueue<Descriptions> queue = new PriorityQueue<Descriptions>(9, comparator);
        for (Map.Entry<String, Integer> entry : descriptions.entrySet())
        {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            queue.add(new Descriptions(entry.getKey(), entry.getValue()));
        }
        String finalString = "";
        for (int i = 0; i < 4; i++) {
            finalString += queue.poll().getDescription().toLowerCase() + ", ";
        }
        finalString += "and " + queue.poll().getDescription().toLowerCase() + ".";
        return finalString;
    }

    public class ValueComparator implements Comparator<Descriptions>
    {
        @Override
        public int compare(Descriptions x, Descriptions y)
        {
            // Assume neither string is null. Real code should
            // probably be more robust
            // You could also just return x.length() - y.length(),
            // which would be more efficient.
            return y.getVoted() - x.getVoted();
        }
    }

    public class Descriptions {

        private String description;
        private int voted;

        public Descriptions(String description, int voted) {
            this.description = description;
            this.voted = voted;
        }

        public String getDescription() {
            return description;
        }

        public int getVoted() {
            return voted;
        }
    }
}
