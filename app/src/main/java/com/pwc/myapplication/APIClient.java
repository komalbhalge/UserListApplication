package com.pwc.myapplication;

import com.pwc.myapplication.Utils.Constants;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**
 * Created by mac_admin on 23/01/17.
 */

public class APIClient {


    public static final String BASE_URL = "https://dl.dropboxusercontent.com/";
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
