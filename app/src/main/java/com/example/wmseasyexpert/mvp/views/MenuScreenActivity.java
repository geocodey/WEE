package com.example.wmseasyexpert.mvp.views;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wmseasyexpert.R;
import com.example.wmseasyexpert.menu.MenuItem;
import com.example.wmseasyexpert.menu.MenuNode;
import com.example.wmseasyexpert.models.screen.BaseScreenData;
import com.example.wmseasyexpert.models.screen.MenuScreenData;
import com.example.wmseasyexpert.utils.Toolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuScreenActivity extends AppCompatActivity {
    private static String TAG = MenuScreenActivity.class.getName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.help_button)
    Button helpButton;
    @BindView(R.id.confirm_button)
    Button confirmButton;
    @BindView(R.id.menu_list)
    ListView menuList;

    private BaseScreenData screenData;
    private AlertDialog alertDialog;
    private String nextScreenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        ButterKnife.bind(this);
        screenData = getScreenData();
        initToolbar();
        initFooter();
        initView();
    }

    private void initView() {
        ArrayAdapter<MenuItem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getMenuList());
        menuList.setAdapter(adapter);
        menuList.setOnItemClickListener((parent, view, position, id) -> {
            MenuItem mi = (MenuItem) menuList.getItemAtPosition(position);
            nextScreenId = mi.getNextScreen();
            confirmButton.setEnabled(true);
        });

    }

    private List<MenuItem> getMenuList() {
        MenuNode menu = (MenuNode) ((MenuScreenData) screenData).getMainNode();
        return menu.getChilds();
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
        alertDialog = new AlertDialog.Builder(MenuScreenActivity.this).create();
        alertDialog.setTitle("Help");
        alertDialog.setMessage(helpMessage);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        confirmButton.setOnClickListener(v -> Toast.makeText(this, nextScreenId, Toast.LENGTH_SHORT).show());
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
