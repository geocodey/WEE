package com.example.wmseasyexpert.network;

import com.example.wmseasyexpert.models.ApiResponse;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.wmseasyexpert.network.NetworkingUtils.getUnsafeOkHttpClient;

public class ApiUtils {
    private static final String BASE_URL = "http://146.185.146.38:8080/wmsee-rest/";


    private static Retrofit retrofitInstance;
    private static Api apiInstance;

    public static Api getApi() {
        if (apiInstance == null) {
            apiInstance = getRetrofitClient().create(Api.class);
        }

        return apiInstance;
    }

    public static Converter<ResponseBody, ApiResponse> getErrorConverter() {
        return ApiUtils.getRetrofitClient().responseBodyConverter(ApiResponse
                .class, new Annotation[0]);
    }

    private static Retrofit getRetrofitClient() {
        if (retrofitInstance == null) {
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getUnsafeOkHttpClient())
                    .build();
        }
        return retrofitInstance;
    }
}
