package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.mareu.model.Meeting;

import java.util.*;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    private Date date = new Calendar.Builder()
            .setDate(2022, 07, 17)
            .setTimeOfDay(14, 0, 0)
            .build().getTime();
    private ArrayList participants = new ArrayList(Arrays.asList("toto", "titi"));
    private Meeting mReunion = new Meeting(1, "Morning", date, "Salle Concorde", participants);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, String.valueOf(mReunion.getMeetingDate()));
        Log.d(TAG,mReunion.getAttendees().get(1));

    }
}