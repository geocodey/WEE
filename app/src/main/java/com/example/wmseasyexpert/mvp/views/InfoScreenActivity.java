package com.example.wmseasyexpert.mvp.views;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wmseasyexpert.R;
import com.example.wmseasyexpert.models.screen.BaseScreenData;
import com.example.wmseasyexpert.utils.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoScreenActivity extends AppCompatActivity {
    private static String TAG = InfoScreenActivity.class.getName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.help_button)
    Button helpButton;
    @BindView(R.id.confirm_button)
    Button confirmButton;
    @BindView(R.id.info_text)
    TextView infoText;

    private BaseScreenData screenData;
    private AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);
        ButterKnife.bind(this);
        screenData = getScreenData();
        initToolbar();
        initFooter();
        initView();
    }

    private void initView() {
        screenData = getScreenData();
        StringBuilder sb = new StringBuilder();
        if (screenData != null) {
            for (String line : screenData.getTextLines()) {
                sb.append(line.replaceAll("(\n)|(\t)", ""));

            }
        }
        infoText.setText(sb);
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
        alertDialog = new AlertDialog.Builder(InfoScreenActivity.this).create();
        alertDialog.setTitle("Help");
        alertDialog.setMessage(helpMessage);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        confirmButton.setOnClickListener(v -> Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show());
    }

    private BaseScreenData getScreenData() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Toast.makeText(this, "Screen data could not be processed.", Toast.LENGTH_SHORT).show();
            return null;
        }
        return (BaseScreenData) extras.getSerializable(BaseScreenData.class.getSimpleName());
    }

}
