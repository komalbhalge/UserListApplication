package com.pwc.myapplication.Retrofit;

import com.pwc.myapplication.ModelClasses.UserContentResponseClass;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mac_admin on 23/01/17.
 */

public interface ApiInterface {

    @GET("facts.json")
    Call<UserContentResponseClass> getUserContent();

}
