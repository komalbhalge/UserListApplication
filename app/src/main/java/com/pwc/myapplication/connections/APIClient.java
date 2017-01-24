package com.pwc.myapplication.connections;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**
 * Created by mac_admin on 23/01/17.
 */

public class APIClient {


    public static final String BASE_URL = "https://dl.dropboxusercontent.com/";
    public static final String GET_CONTENT_URL =  "u/746330/facts.json";

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
