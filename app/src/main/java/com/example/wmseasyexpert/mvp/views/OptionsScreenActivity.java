package com.example.wmseasyexpert.mvp.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wmseasyexpert.R;
import com.example.wmseasyexpert.models.screen.BaseScreenData;
import com.example.wmseasyexpert.models.screen.Option;
import com.example.wmseasyexpert.models.screen.OptionsScreenData;
import com.example.wmseasyexpert.parser.TestXMLs;
import com.example.wmseasyexpert.parser.XMLParser;
import com.example.wmseasyexpert.utils.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionsScreenActivity extends AppCompatActivity {
    private static String TAG = OptionsScreenActivity.class.getName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.options_list)
    ListView optionsList;
    @BindView(R.id.help_button)
    Button helpButton;
    @BindView(R.id.confirm_button)
    Button confirmButton;


    private OptionsScreenData screenData;
    private AlertDialog alertDialog;
    private String nextScreenId;

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
        initFooter();
        ArrayAdapter<Option> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, screenData.getOptions());
        optionsList.setAdapter(adapter);
        optionsList.setOnItemClickListener((parent, view, position, id) -> {
            Option opt = (Option) optionsList.getItemAtPosition(position);
            nextScreenId = opt.getValue();
            confirmButton.setEnabled(true);
        });
    }

    private void initToolbar() {
        toolbar.setTitle(screenData.getTitle() + "(" + screenData.getScreenTag().getId() + ")");
        toolbar.setOnBackClickListener(this::previousScreen);
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
        confirmButton.setOnClickListener(v -> Toast.makeText(this, nextScreenId, Toast.LENGTH_SHORT).show());
    }

    private void previousScreen() {
        Intent intent = new Intent(OptionsScreenActivity.this, InfoScreenActivity.class);
        BaseScreenData screenData = XMLParser.parseDoc(TestXMLs.infoXML);
        intent.putExtra(BaseScreenData.class.getSimpleName(), screenData);
        OptionsScreenActivity.this.startActivity(intent);
    }

    private OptionsScreenData getScreenData() {
        return (OptionsScreenData) Objects.requireNonNull(getIntent().getExtras()).getSerializable(BaseScreenData.class.getSimpleName());
    }


}
