package com.example.mareu.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import com.example.mareu.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.mareu.databinding.FragmentMeetingBinding;
import com.example.mareu.model.Meeting;
import com.example.mareu.event.CancelMeetingEvent;

import java.util.List;

import org.greenrobot.eventbus.EventBus;


/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings ;

    public MyMeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentMeetingBinding view = FragmentMeetingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.mItem = mMeetings.get(position);
      //  holder.mIdView.setText(mMeetings.get(position).getId());
        holder.mContentView.setText(mMeetings.get(position).getSubject()
                + " - "
                + mMeetings.get(position).getMeetingDate().getHours()
                + "h"
                + mMeetings.get(position).getMeetingDate().getMinutes()
                + " - "
                + mMeetings.get(position).getPlace()
        );
       holder.mContentViewAttendees.setText((mMeetings.get(position).getAttendees().toString()));

       holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new CancelMeetingEvent(meeting));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mContentViewAttendees;
        public final ImageView mDeleteButton;
        public Meeting mItem;

        public ViewHolder(FragmentMeetingBinding binding) {
            super(binding.getRoot());
           // mIdView = binding.imageView;
            mContentView = binding.content;
            mContentViewAttendees = binding.contentAttendees;
            mDeleteButton = binding.imageView;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}