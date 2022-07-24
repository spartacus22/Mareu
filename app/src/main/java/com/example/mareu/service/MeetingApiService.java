package com.example.mareu.service;

import com.example.mareu.model.Meeting;
import java.util.List;

/**
 * Meeting API client
 */

public interface MeetingApiService {

    /**
     * Get all my Meetings
     *
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * Get all my Meetings sorted by Date
     *
     * @return {@link List}
     */
    List<Meeting> getMeetingsByDate();

    /**
     * Get all my Meetings sorted by Location
     *
     * @return {@link List}
     */
    List<Meeting> getMeetingsByPlace();

    /**
     * Create a meeting
     *
     * @param meeting
     */
    void createMeeting(Meeting meeting);

    /**
     * Get all my Meetings sorted by Location
     *
     * @param meeting
     */
    void cancelMeeting(Meeting meeting);

}
