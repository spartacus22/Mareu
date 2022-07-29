package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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
    private ActivityMainBinding binding;
    private MyMeetingRecyclerViewAdapter adapter;
    private Spinner mySpinner;
    private String[] categories = {"Date", "Location"};
    private static String filtre = "Date";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        mApiService = DI.getMeetingApiService();
        mMeetings = mApiService.getMeetings();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mySpinner = binding.spinner;
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                categories);
        // Layout for All ROWs of Spinner.  (Optional for ArrayAdapter).
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapterSpinner);
        // When user select a List-Item.
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Recycler view
        adapter = new MyMeetingRecyclerViewAdapter(mMeetings);
        binding.RView.setAdapter(adapter);
        binding.RView.setLayoutManager(new LinearLayoutManager(this));

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddMeetingActivity.navigate(MainActivity.this);
               // Log.d(TAG, "View Binding press button");
            }
        });

        mMeetings = mApiService.getMeetingsByDate();
        for(Meeting m : mMeetings){
            Log.d(TAG,m.getSubject() + " - " + m.getMeetingDate());
        }
        mMeetings = mApiService.getMeetingsByPlace();
        for(Meeting m : mMeetings){
            Log.d(TAG,m.getSubject() + " - " + m.getPlace());
        }
    }

    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        String categorie = (String) adapter.getItem(position);
        Log.d(TAG,categorie);
        initList(categorie);
        
                //Toast.makeText(getApplicationContext(), "Selected categorie ",Toast.LENGTH_SHORT).show();
    }

    private void initList(String filtre) {
        mApiService = DI.getMeetingApiService();
        if (filtre == "Date") {
            mMeetings = mApiService.getMeetingsByDate();
        } else {
            mMeetings = mApiService.getMeetingsByPlace();
        }
        adapter = new MyMeetingRecyclerViewAdapter(mMeetings);
        binding.RView.setAdapter(adapter);
        binding.RView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onResume() {
        super.onResume();
        initList(filtre);
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
        initList(filtre);
    }

}