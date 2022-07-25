package com.example.mareu.ui;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;
import com.example.mareu.databinding.ActivityAddMeetingBinding;


public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, AddMeetingActivity.class);
        ActivityCompat.startActivity(context, intent, null);
    }
}