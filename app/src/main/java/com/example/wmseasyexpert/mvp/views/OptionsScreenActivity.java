package com.example.wmseasyexpert.mvp.views;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import com.example.wmseasyexpert.R;
import com.example.wmseasyexpert.models.screen.BaseScreenData;
import com.example.wmseasyexpert.models.screen.Option;
import com.example.wmseasyexpert.models.screen.OptionsScreenData;
import com.example.wmseasyexpert.utils.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionsScreenActivity extends BaseScreenActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.options_list)
    ListView optionsList;
    @BindView(R.id.help_button)
    Button helpButton;
    @BindView(R.id.confirm_button)
    Button confirmButton;


    private BaseScreenData screenData;
    private AlertDialog alertDialog;
    private String nextScreenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_screen);
        ButterKnife.bind(this);
        screenData = getExtraData();
        initToolbar();
        initFooter();
        initView();
    }

    private void initView() {
        ArrayAdapter<Option> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, ((OptionsScreenData) screenData).getOptions());
        optionsList.setAdapter(adapter);
        optionsList.setOnItemClickListener((parent, view, position, id) -> {
            Option opt = (Option) optionsList.getItemAtPosition(position);
            nextScreenId = opt.getValue();
            confirmButton.setEnabled(true);
        });
    }

    private void initToolbar() {
        toolbar.setOnBackClickListener(this::onBackPressed);
        if (screenData == null) {
            return;
        }
        toolbar.setTitle(screenData.getTitle() + "(" + screenData.getScreenTag().getId() + ")");
    }

    private void initFooter() {
        String helpKey = screenData.getHelpTag().getHelpKey();
        String helpMessage = TextUtils.join("", screenData.getHelpTag().getHelpLines());
        helpButton.setText(helpKey);
        helpButton.setOnClickListener(v -> alertDialog.show());
        alertDialog = new AlertDialog.Builder(OptionsScreenActivity.this).create();
        alertDialog.setTitle("Help");
        alertDialog.setMessage(helpMessage);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        confirmButton.setOnClickListener(v -> nextScreen());
    }
}
