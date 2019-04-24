package com.example.wmseasyexpert.mvp.views;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.wmseasyexpert.R;
import com.example.wmseasyexpert.models.screen.OptionsScreenData;

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
        setSupportActionBar(toolbar);
        screenData = getScreenData();
        Log.d(TAG, String.valueOf(screenData));
        Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
    }

    private OptionsScreenData getScreenData() {
        return (OptionsScreenData) Objects.requireNonNull(getIntent().getExtras()).getSerializable(OptionsScreenData.class.getSimpleName());
    }

}
