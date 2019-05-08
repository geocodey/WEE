package com.example.wmseasyexpert.mvp.presenters;

import android.util.Log;

import com.example.wmseasyexpert.models.screen.BaseScreenData;
import com.example.wmseasyexpert.models.screen.ScreenResponse;
import com.example.wmseasyexpert.mvp.contracts.HomeContract;
import com.example.wmseasyexpert.network.interactors.GetScreenNetworkInteractor;
import com.example.wmseasyexpert.parser.TestXMLs;
import com.example.wmseasyexpert.parser.XMLParser;

public class HomePresenter implements HomeContract.Presenter {
    private static String TAG = HomePresenter.class.getName();
    private final HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void getScreen() {
        BaseScreenData screenData = XMLParser.parseDoc(TestXMLs.inputXML);
        view.displayScreen(screenData);
        /*GetScreenNetworkInteractor.execute(new GetScreenNetworkInteractor.GetScreenResponseCallback() {
            @Override
            public void onGetScreenResponseSuccess(ScreenResponse response) {
                BaseScreenData screenData = XMLParser.parseDoc(response.getResult());
                view.displayScreen(screenData);
            }

            @Override
            public void onGetScreenResponseGenericError() {
                view.showOnGetScreenError();
            }

        });
*/
    }
}
