package com.example.wmseasyexpert.mvp.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wmseasyexpert.models.screen.BaseScreenData;
import com.example.wmseasyexpert.parser.TestXMLs;
import com.example.wmseasyexpert.parser.XMLParser;
import com.example.wmseasyexpert.screen.ScreenType;

import java.util.ArrayList;
import java.util.Random;

public abstract class BaseScreenActivity extends AppCompatActivity {

    protected String TAG = this.getClass().getName();
    ArrayList<BaseScreenData> screens = new ArrayList<>();

    public BaseScreenActivity() {
        screens.add(XMLParser.parseDoc(TestXMLs.infoXML));
        screens.add(XMLParser.parseDoc(TestXMLs.inputXML));
        screens.add(XMLParser.parseDoc(TestXMLs.optionsXML));
        screens.add(XMLParser.parseDoc(TestXMLs.menuXML));
    }

    protected BaseScreenData getExtraData() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Toast.makeText(this, "Screen data could not be processed.", Toast.LENGTH_SHORT).show();
            return null;
        }
        return (BaseScreenData) extras.getSerializable(BaseScreenData.class.getSimpleName());
    }

    protected void displayScreen(BaseScreenData screenData) {
        if (screenData == null) {
            Log.e(TAG, "screen data received is null.");
            return;
        }
        Intent intent = null;
        String type = screenData.getScreenTag().getType();
        switch (type) {
            case ScreenType.INFO:
                intent = new Intent(BaseScreenActivity.this, InfoScreenActivity.class);
                break;
            case ScreenType.INPUT:
                intent = new Intent(BaseScreenActivity.this, InputScreenActivity.class);
                break;
            case ScreenType.MENU:
                intent = new Intent(BaseScreenActivity.this, MenuScreenActivity.class);
                break;
            case ScreenType.OPTIONS:
                intent = new Intent(BaseScreenActivity.this, OptionsScreenActivity.class);
                break;
            default:
                Toast.makeText(this, "Wrong screen type found.", Toast.LENGTH_SHORT).show();
                break;

        }
        if (intent != null) {
            intent.putExtra(BaseScreenData.class.getSimpleName(), screenData);
            BaseScreenActivity.this.startActivity(intent);
        }
    }

    protected void nextScreen() {
        int nextScreen = new Random().nextInt(4);
        Log.d(TAG, String.valueOf(nextScreen));
        displayScreen(screens.get(nextScreen));
    }
}
