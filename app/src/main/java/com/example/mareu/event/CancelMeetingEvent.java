package com.example.mareu.event;

import com.example.mareu.model.Meeting;

public class CancelMeetingEvent {

    /**
     * Event fired when a user meeting is cancelled
     */

    /**
     * Meeting to delete
     */
    public Meeting meeting;

    /**
     * Constructor.
     *
     * @param meeting
     */
    public CancelMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }


}
