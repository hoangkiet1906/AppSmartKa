package com.rajendra.onlinedailygroceries.network;


import com.rajendra.onlinedailygroceries.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
    private static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://api.larntech.net/")
                .baseUrl("http://"+ Constants.Host +":8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }

    public static NewsService getNewsService() {
        NewsService newsService = getRetrofit().create(NewsService.class);
        return newsService;
    }
}
