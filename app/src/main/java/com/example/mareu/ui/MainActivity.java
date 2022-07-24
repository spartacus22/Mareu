package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mareu.DI.DI;
import com.example.mareu.databinding.ActivityMainBinding;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.event.CancelMeetingEvent;

import java.util.*;
import java.util.Date;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    private MyMeetingRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        mApiService = DI.getMeetingApiService();
        mMeetings = mApiService.getMeetings();
        adapter = new MyMeetingRecyclerViewAdapter(mMeetings);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.RView.setAdapter(adapter);
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

    private void initList() {
        mApiService = DI.getMeetingApiService();
        mMeetings = mApiService.getMeetings();
        adapter = new MyMeetingRecyclerViewAdapter(mMeetings);
        binding.RView.setAdapter(adapter);
        binding.RView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onCancelMeeting(CancelMeetingEvent event) {
        mApiService.cancelMeeting(event.meeting);
        initList();
    }

}