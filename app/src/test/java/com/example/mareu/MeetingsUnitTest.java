package com.example.mareu;

import org.junit.FixMethodOrder;
import org.junit.Test;

//import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import com.example.mareu.DI.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.DummyMeetingsGenerator;
import com.example.mareu.service.MeetingApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.util.Calendar;
import java.util.List;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4.class)
public class MeetingsUnitTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void test1GetMeetingsWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingsGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void test2CancelMeetingWithSuccess() {
        Meeting meetingToCancel = service.getMeetings().get(0);
        service.cancelMeeting(meetingToCancel);
        assertFalse(service.getMeetings().contains(meetingToCancel));
    }

    @Test
    public void test3AddMeetingWithSuccess() {
        Meeting meetingToAdd = new Meeting(1,
                "Réunion Z",
                new Calendar.Builder()
                        .setDate(2022, 07, 22)
                        .setTimeOfDay(14, 0, 0)
                        .build().getTime(),
                "Paris",
                "toto@lamzone.com, titi@lamzone.com");
        service.createMeeting(meetingToAdd);
        assertTrue(service.getMeetings().contains(meetingToAdd));
    }

    @Test
    public void test4FilterByLocationWithSuccess(){
        List<Meeting> meetings = service.getMeetingsByPlace();
        assertEquals("Luigi", meetings.get(0).getPlace());
        assertEquals("Mario", meetings.get(1).getPlace());
        assertEquals("Peach", meetings.get(2).getPlace());
    }

    @Test
    public void test5FilterByDateWithSuccess(){
        List<Meeting> meetings = service.getMeetingsByDate();
        assertEquals("Réunion A", meetings.get(0).getSubject());
        assertEquals("Réunion C", meetings.get(1).getSubject());
        assertEquals("Réunion B", meetings.get(2).getSubject());
    }

}