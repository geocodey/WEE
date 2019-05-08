package com.example.wmseasyexpert.mvp.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.wmseasyexpert.R;
import com.example.wmseasyexpert.models.screen.BaseScreenData;
import com.example.wmseasyexpert.mvp.contracts.HomeContract;
import com.example.wmseasyexpert.mvp.presenters.HomePresenter;
import com.example.wmseasyexpert.screen.ScreenType;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    private static final String TAG = HomeActivity.class.getName();
    private HomePresenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        presenter = new HomePresenter(this);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getScreen();
    }

    @Override
    public void displayScreen(BaseScreenData screenData) {
        if (screenData == null) {
            Log.e(TAG, "screen data received is null.");
            return;
        }
        Intent intent = null;
        String type = screenData.getScreenTag().getType();
        switch (type) {
            case ScreenType.INFO:
                intent = new Intent(HomeActivity.this, InfoScreenActivity.class);
                break;
            case ScreenType.INPUT:
                intent = new Intent(HomeActivity.this, InputScreenActivity.class);
                break;
            case ScreenType.MENU:
                intent = new Intent(HomeActivity.this, MenuScreenActivity.class);
                break;
            case ScreenType.OPTIONS:
                intent = new Intent(HomeActivity.this, OptionsScreenActivity.class);
                break;
            default:
                Toast.makeText(this, "Wrong screen type found.", Toast.LENGTH_SHORT).show();
                break;

        }
        if (intent != null) {
            intent.putExtra(BaseScreenData.class.getSimpleName(), screenData);
            HomeActivity.this.startActivity(intent);
        }
    }

    @Override
    public void showOnGetScreenError() {
        Toast.makeText(this, "error on get screen", Toast.LENGTH_SHORT).show();
    }
}
