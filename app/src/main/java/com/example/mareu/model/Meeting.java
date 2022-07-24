package com.example.mareu.model;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

/**
 * Model object representing a Neighbour
 */

public class Meeting implements Serializable {

    /**
     * Identifier
     */
    private long id;

    /**
     * Full subject
     */
    private String subject;

    /**
     * Place
     */
    private String place;

    /**
     * Date
     */
    private Date meetingDate;

    /**
     * Attendees
     */
    private ArrayList<String> attendees;


    /**
     * Constructor
     *
     * @param id
     * @param subject
     * @param meetingDate
     */
    public Meeting(long id, String subject, Date meetingDate, String place, ArrayList<String> attendees) {
        this.id = id;
        this.subject = subject;
        this.meetingDate = meetingDate;
        this.place = place;
        this.attendees = attendees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String address) {
        this.place = place;
    }

    public ArrayList<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(ArrayList<String> attendees) {
        this.attendees = attendees;
    }

}

