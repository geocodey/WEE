package com.example.wmseasyexpert.mvp.views;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wmseasyexpert.R;
import com.example.wmseasyexpert.models.screen.BaseScreenData;
import com.example.wmseasyexpert.utils.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InputScreenActivity extends AppCompatActivity {
    private static String TAG = InputScreenActivity.class.getName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.help_button)
    Button helpButton;
    @BindView(R.id.check_button)
    ImageView checkButton;
    @BindView(R.id.input_field)
    EditText inputField;

    private BaseScreenData screenData;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_screen);
        ButterKnife.bind(this);
        screenData = getScreenData();
        initToolbar();
        initFooter();
        initView();
    }

    private void initView() {
        screenData = getScreenData();
    }

    private void initToolbar() {
        toolbar.setOnBackClickListener(this::onBackPressed);
        if (screenData == null) {
            return;
        }
        toolbar.setTitle(screenData.getTitle() + "(" + screenData.getScreenTag().getId() + ")");
    }

    private void initFooter() {
        if (screenData == null) {
            return;
        }
        String helpKey = screenData.getHelpTag().getHelpKey();
        String helpMessage = TextUtils.join("", screenData.getHelpTag().getHelpLines());
        helpButton.setText(helpKey);
        helpButton.setOnClickListener(v -> alertDialog.show());
        alertDialog = new AlertDialog.Builder(InputScreenActivity.this).create();
        alertDialog.setTitle("Help");
        alertDialog.setMessage(helpMessage);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        checkButton.setOnClickListener(v -> Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show());
    }

    private BaseScreenData getScreenData() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Toast.makeText(this, "Screen data could not be processed.", Toast.LENGTH_SHORT).show();
            return null;
        }
        return (BaseScreenData) extras.getSerializable(BaseScreenData.class.getSimpleName());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

}
