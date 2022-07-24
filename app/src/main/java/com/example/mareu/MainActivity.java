package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mareu.DI.DI;
import com.example.mareu.databinding.ActivityMainBinding;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.DummyMeetingApiService;
import com.example.mareu.service.MeetingApiService;

import java.util.*;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    private static MeetingApiService mApiService;
    private List<Meeting> mMeetings;
    private Date date = new Calendar.Builder()
            .setDate(2022, 07, 17)
            .setTimeOfDay(14, 0, 0)
            .build().getTime();
    private ArrayList participants = new ArrayList(Arrays.asList("toto", "titi"));
    private Meeting mReunion = new Meeting(1, "Morning", date, "Salle Concorde", participants);

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        mApiService = DI.getMeetingApiService();
        mMeetings = mApiService.getMeetings();
        MyMeetingRecyclerViewAdapter mRecycler = new MyMeetingRecyclerViewAdapter(mMeetings);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.RView.setAdapter(mRecycler);

        binding.RView.setLayoutManager(new LinearLayoutManager(this));

        Log.d(TAG, String.valueOf(mReunion.getMeetingDate()));
        Log.d(TAG,mReunion.getAttendees().get(1));


        mMeetings = mApiService.getMeetingsByDate();
        for(Meeting m : mMeetings){
            Log.d(TAG,m.getSubject() + " - " + m.getMeetingDate());
        }
        mMeetings = mApiService.getMeetingsByPlace();
        for(Meeting m : mMeetings){
            Log.d(TAG,m.getSubject() + " - " + m.getPlace());
        }

    }
}