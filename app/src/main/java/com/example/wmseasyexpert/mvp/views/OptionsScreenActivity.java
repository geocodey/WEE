package com.example.wmseasyexpert.mvp.views;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.wmseasyexpert.R;
import com.example.wmseasyexpert.models.screen.OptionsScreenData;
import com.example.wmseasyexpert.utils.Toolbar;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionsScreenActivity extends AppCompatActivity {
    private static String TAG = OptionsScreenActivity.class.getName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    OptionsScreenData screenData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_screen);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        screenData = getScreenData();
        initToolbar();
        Log.d(TAG, String.valueOf(screenData));
        Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
    }

    private void initToolbar() {

        toolbar.setTitle(screenData.getTitle() + "(" + screenData.getScreenTag().getId()+ ")");
        toolbar.setOnBackClickListener(this::previousScreen);
    }

    private void previousScreen() {
        Log.d(TAG, "Backk");
    }

    private OptionsScreenData getScreenData() {
        return (OptionsScreenData) Objects.requireNonNull(getIntent().getExtras()).getSerializable(OptionsScreenData.class.getSimpleName());
    }


}
