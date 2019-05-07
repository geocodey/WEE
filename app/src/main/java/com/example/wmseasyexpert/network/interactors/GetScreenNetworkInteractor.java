package com.example.wmseasyexpert.network.interactors;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.wmseasyexpert.models.screen.ScreenResponse;
import com.example.wmseasyexpert.network.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetScreenNetworkInteractor {
    private static final String TAG = GetScreenNetworkInteractor.class.getName();

    public static void execute(@NonNull final GetScreenResponseCallback callback) {
        ApiUtils.getApi().getScreenResponse().enqueue(new Callback<ScreenResponse>() {
            @Override
            public void onResponse(@NonNull Call<ScreenResponse> call, @NonNull Response<ScreenResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "successful request " + response.body());
                        callback.onGetScreenResponseSuccess(response.body());
                    } else {
                        Log.e(TAG, "request error " + response.toString());
                        callback.onGetScreenResponseGenericError();
                    }
                } else {
                    Log.e(TAG, "request error " + response.toString());
                    callback.onGetScreenResponseGenericError();
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                callback.onGetScreenResponseGenericError();
                Log.e(TAG, "onFailure " + t);
            }
        });
    }

    public interface GetScreenResponseCallback {
        void onGetScreenResponseSuccess(ScreenResponse response);

        void onGetScreenResponseGenericError();

    }
}
