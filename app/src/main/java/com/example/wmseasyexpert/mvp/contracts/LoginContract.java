package com.example.wmseasyexpert.mvp.contracts;

import com.example.wmseasyexpert.models.screen.BaseScreenData;

public interface LoginContract {
    interface View {
        void showProgress(boolean show);

        void showWrongPasswordError();

        void displayScreen(BaseScreenData screenData);

        void showOnGetScreenError();

    }

    interface Presenter {
        void executeLogin(String email, String password);
    }
}
