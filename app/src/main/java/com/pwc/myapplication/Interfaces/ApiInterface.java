package com.pwc.myapplication.Interfaces;

import com.pwc.myapplication.ModelClasses.UserContentResponseModel;

import retrofit.Call;
import retrofit.http.GET;


/**
 * Created by mac_admin on 23/01/17.
 */

public interface ApiInterface {

    @GET("u/746330/facts.json")
    Call<UserContentResponseModel> getUserContent();

}
