package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.util.Calendar;

public abstract class DummyMeetingsGenerator {

    Date date = new Calendar.Builder()
            .setDate(2022, 07, 17)
            .setTimeOfDay(14, 0, 0)
            .build().getTime();

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(
                    1,
                    "Réunion A",
                    new Calendar.Builder()
                    .setDate(2022, 07, 17)
                    .setTimeOfDay(14, 0, 0)
                    .build().getTime(),
                    "Peach",
                    new ArrayList(Arrays.asList("maxime@lamzone.com", "alexis@lamzone.com"))
            ),
            new Meeting(
                    2,
                    "Réunion B",
                    new Calendar.Builder()
                            .setDate(2022, 07, 17)
                            .setTimeOfDay(16, 0, 0)
                            .build().getTime(),
                    "Mario",
                    new ArrayList(Arrays.asList("paul@lamzone.com", "viviane@lamzone.com"))
            ),
            new Meeting(
                    3,
                    "Réunion C",
                    new Calendar.Builder()
                            .setDate(2022, 07, 17)
                            .setTimeOfDay(19, 0, 0)
                            .build().getTime(),
                    "Luigi",
                    new ArrayList(Arrays.asList("amandine@lamzone.com", "luc@lamzone.com"))));

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

}
