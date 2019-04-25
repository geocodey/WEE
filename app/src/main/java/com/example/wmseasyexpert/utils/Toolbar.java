package com.example.wmseasyexpert.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.wmseasyexpert.R;

/**
 * Copyright (c) 2019 WEE. All rights reserved.
 */

public class Toolbar extends RelativeLayout {

    private TextView title;
    private View backButton;

    public Toolbar(Context context) {
        super(context);
        initView();
    }

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        RelativeLayout toolbar = (RelativeLayout) inflate(getContext(), R.layout.toolbar, this);
        backButton = toolbar.findViewById(R.id.toolbar_back);
        title = toolbar.findViewById(R.id.toolbar_title);

        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
    }

    public void setTitle(String titleText) {
        title.setVisibility(VISIBLE);
        title.setText(titleText);
    }

    public void setOnBackClickListener(OnBackClickListener listener) {
        if (listener != null) {
            backButton.setVisibility(VISIBLE);
            backButton.setOnClickListener(v -> listener.onBackClick());
        } else {
            backButton.setVisibility(GONE);
        }
    }

    public interface OnBackClickListener {
        void onBackClick();
    }

}

