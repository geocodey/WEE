package com.example.wmseasyexpert.network;

import com.example.wmseasyexpert.models.screen.ScreenResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("start_session")
    Call<ScreenResponse> getScreenResponse();
}
