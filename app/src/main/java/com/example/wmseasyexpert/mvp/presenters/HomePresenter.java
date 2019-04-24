package com.example.wmseasyexpert.mvp.presenters;

import android.util.Log;

import com.example.wmseasyexpert.models.screen.BaseScreenData;
import com.example.wmseasyexpert.mvp.contracts.HomeContract;
import com.example.wmseasyexpert.parser.TestXMLs;
import com.example.wmseasyexpert.parser.XMLParser;

public class HomePresenter implements HomeContract.Presenter{
    private static String TAG = HomePresenter.class.getName();
    private final HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void getScreen() {
        BaseScreenData screenData = XMLParser.parseDoc(TestXMLs.optionsXML);
        view.displayScreen(screenData);
    }
}
