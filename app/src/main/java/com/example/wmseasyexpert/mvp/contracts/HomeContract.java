package com.example.wmseasyexpert.mvp.contracts;

import com.example.wmseasyexpert.models.screen.BaseScreenData;

public interface HomeContract {
    interface View {
        void displayScreen(BaseScreenData screenData);

        void showOnGetScreenError();
    }

    interface Presenter {
        void getScreen();
    }
}
