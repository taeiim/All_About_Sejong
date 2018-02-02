package com.example.parktaeim.all_about_sejong;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by parktaeim on 2018. 1. 28..
 */

public class ServiceGenerator {
    private static final String API_BASE_URL = "http://data.sejong.go.kr/";

    private static Retrofit getBathroomListInstance(){
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getListApiService(){
        return getBathroomListInstance().create(ApiService.class);
    }
}
