package com.example.mareu.service;

import android.util.Log;

import com.example.mareu.model.Meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingsGenerator.generateMeetings();
    private static final String TAG = "MyActivity";

    @Override
    public List<Meeting> getMeetings(){
        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsByDate(){

        Collections.sort(meetings, new Comparator<Meeting>() {
            public int compare(Meeting m1, Meeting m2) {
                if (m1.getMeetingDate() == null || m2.getMeetingDate() == null)
                    return 0;
                return m1.getMeetingDate().compareTo(m2.getMeetingDate());
            }
        });
        return meetings;
    }

    /**
     * Get all my Meetings sorted by Location
     * @return {@link List}
     */
    public List<Meeting> getMeetingsByPlace(){
        Collections.sort(meetings, new Comparator<Meeting>() {
            public int compare(Meeting m1, Meeting m2) {
                if (m1.getPlace() == null || m2.getPlace() == null)
                    return 0;
                return m1.getPlace().compareTo(m2.getPlace());
            }
        });
        return meetings;
    }

    /**
     * Create a meeting
     * @param meeting
     */
    public void createMeeting(Meeting meeting){
        meetings.add(meeting);
    }

    /**
     * Get all my Meetings sorted by Location
     * @param meeting
     */
    public void cancelMeeting(Meeting meeting){
        meetings.remove(meeting);
    }

    /**
     * Filter all my Meetings by Location
     * @param place
     */
    @Override
    public List<Meeting> filterMeetingsByLocation(String place) {
        List<Meeting> filteredList = new ArrayList<>();
        for (Meeting m: meetings)
        {
            if (m.getPlace().equals(place)) {
                filteredList.add(m);
            }
        }
        if (place.equals("")) {
            return meetings;
        } else {
            return filteredList;
        }
    }


    /**
     * Filter all my Meetings by Location
     * @param date
     */
    @Override
    public List<Meeting> filterMeetingsByDate(String date) {

        SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date newDate = new Date();
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Meeting> filteredList = new ArrayList<>();
        for (Meeting m: meetings)
        {
            Log.d(TAG, m.getMeetingDate() + " " + newDate);
            if (m.getMeetingDate().equals(newDate)) {
                filteredList.add(m);
            }
        }
        if (date.equals("")) {
            return meetings;
        } else {
            return filteredList;
        }
    }


}
