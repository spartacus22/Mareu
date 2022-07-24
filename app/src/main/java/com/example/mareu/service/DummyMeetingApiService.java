package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingsGenerator.generateMeetings();

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


}
