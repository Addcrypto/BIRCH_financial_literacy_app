package com.example.birch.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LinkTokenRequester {
    private static LinkTokenRequester instance = null;
    private static LinkApi linkAPI;

    private final static String BASE_URL = "http://10.0.2.2:8000/";

    private LinkTokenRequester(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        linkAPI = retrofit.create(LinkApi.class);
    }

    public static LinkTokenRequester getInstance(){
        if(instance == null){
            instance = new LinkTokenRequester();
        }
        return instance;
    }

    public static LinkApi getLinkAPI(){
        return linkAPI;
    }
}
