package com.pwc.myapplication.interfaces;

import com.pwc.myapplication.connections.APIClient;
import com.pwc.myapplication.models.UserContentResponseModel;

import retrofit.Call;
import retrofit.http.GET;


/**
 * Created by mac_admin on 23/01/17.
 */

public interface ApiInterface {

    @GET(APIClient.GET_CONTENT_URL)
    Call<UserContentResponseModel> getUserContent();

}
