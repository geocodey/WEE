package com.example.wmseasyexpert.mvp.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wmseasyexpert.R;
import com.example.wmseasyexpert.utils.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoScreenActivity extends AppCompatActivity {
    private static String TAG = InfoScreenActivity.class.getName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.help_button)
    Button helpButton;
    @BindView(R.id.check_button)
    ImageView checkButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);
        ButterKnife.bind(this);
    }

}
