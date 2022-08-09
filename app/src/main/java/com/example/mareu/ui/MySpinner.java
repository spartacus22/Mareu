package com.example.mareu.ui;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatSpinner;

public class MySpinner extends AppCompatSpinner {

    private int lastPosition = 0;

    public MySpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setSelection(int position, boolean animate) {
        OnItemSelectedListener listener = getOnItemSelectedListener();
        setOnItemSelectedListener(null);
        super.setSelection(position, animate);
        lastPosition = position;
        setOnItemSelectedListener(listener);
    }

    @Override
    public void setSelection(int position) {
        super.setSelection(position);
        boolean sameSelected = lastPosition == getSelectedItemPosition();
        OnItemSelectedListener onItemSelectedListener = getOnItemSelectedListener();
        if (sameSelected && onItemSelectedListener != null) {
            onItemSelectedListener.onItemSelected(this, getSelectedView(), position, getSelectedItemId());
        }
        lastPosition = position;
    }
}