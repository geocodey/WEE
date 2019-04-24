package com.example.wmseasyexpert.mvp.contracts;

public interface HomeContract {
    interface View {
        void displayScreen();

        void showOnGetScreenError();
    }

    interface Presenter {
        void getScreen();
    }
}
