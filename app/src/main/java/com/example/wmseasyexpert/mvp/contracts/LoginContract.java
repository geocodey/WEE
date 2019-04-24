package com.example.wmseasyexpert.mvp.contracts;

public interface LoginContract {
    interface View {
        void proceedToHome();

        void showProgress(boolean show);

        void showWrongPasswordError();

    }

    interface Presenter {
        void executeLogin(String email,String password);
    }
}
