package com.example.wmseasyexpert.mvp.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.wmseasyexpert.R;
import com.example.wmseasyexpert.mvp.contracts.HomeContract;
import com.example.wmseasyexpert.mvp.presenters.HomePresenter;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    private static final String TAG = HomeActivity.class.getName();
    private HomePresenter presenter;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @OnClick(R.id.fab) void fabClickListener(View v) {
        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        presenter = new HomePresenter(this);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart(){
        super.onStart();
        presenter.getScreen();
    }

    @Override
    public void displayScreen() {
        Toast.makeText(this,"Display screen called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showOnGetScreenError() {
        Toast.makeText(this,"error on get screen",Toast.LENGTH_SHORT).show();
    }
}
