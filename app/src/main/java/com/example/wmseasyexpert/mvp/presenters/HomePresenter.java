package com.example.wmseasyexpert.mvp.presenters;

import com.example.wmseasyexpert.mvp.contracts.HomeContract;

public class HomePresenter implements HomeContract.Presenter{
    private final HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void getScreen() {
        view.displayScreen();
    }
}
