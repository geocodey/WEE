package com.example.wmseasyexpert.mvp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.wmseasyexpert.R;
import com.example.wmseasyexpert.models.screen.BaseScreenData;
import com.example.wmseasyexpert.models.screen.OptionsScreenData;
import com.example.wmseasyexpert.mvp.contracts.HomeContract;
import com.example.wmseasyexpert.mvp.presenters.HomePresenter;
import com.example.wmseasyexpert.screen.ScreenType;
import com.google.android.material.snackbar.Snackbar;

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
    public void displayScreen(BaseScreenData screenData) {
        if(screenData.getScreenTag().getType().equals(ScreenType.OPTIONS)){
            Intent intent = new Intent(HomeActivity.this, OptionsScreenActivity.class);
            intent.putExtra(OptionsScreenData.class.getSimpleName(), screenData);
            HomeActivity.this.startActivity(intent);
        }
        Toast.makeText(this,"Display screen called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showOnGetScreenError() {
        Toast.makeText(this,"error on get screen",Toast.LENGTH_SHORT).show();
    }
}
