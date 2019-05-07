package com.example.wmseasyexpert;

import android.app.Application;

public class WeeApplication extends Application {
    private static WeeApplication instance;

    public static WeeApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

    }
}
