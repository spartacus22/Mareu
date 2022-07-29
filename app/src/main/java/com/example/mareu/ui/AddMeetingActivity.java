package com.example.mareu.ui;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

import com.example.mareu.DI.DI;
import com.example.mareu.databinding.ActivityAddMeetingBinding;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    private MeetingApiService mApiService;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mApiService = DI.getMeetingApiService();

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String date = "Mar 10, 2016 6:30:00 PM";
                String date = binding.dateLyt.getEditText().getText().toString()
                        + " "
                        +  binding.hoursLyt.getEditText().getText().toString();
                //SimpleDateFormat spf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");
                SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                Date newDate = new Date();
                try {
                    newDate = spf.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //spf= new SimpleDateFormat("dd MMM yyyy");
                //date = spf.format(newDate);

                Log.d(TAG, date);
                //Log.d(TAG, binding.dateLyt.getEditText().getText().toString());
                Meeting meet = new Meeting(
                        System.currentTimeMillis(),
                        binding.subjectLyt.getEditText().getText().toString(),
                        new Calendar.Builder()
                                .setInstant(newDate)
                                .build().getTime(),
                        binding.locationLyt.getEditText().getText().toString(),
                        binding.attendeesLyt.getEditText().getText().toString()
                );
                mApiService.createMeeting(meet);
                finish();
                Log.d(TAG, "View Binding press button");
            }
        });
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, AddMeetingActivity.class);
        ActivityCompat.startActivity(context, intent, null);
    }
}