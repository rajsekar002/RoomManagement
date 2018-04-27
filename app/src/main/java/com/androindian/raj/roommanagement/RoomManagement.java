package com.androindian.raj.roommanagement;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Raj on 4/21/2018.
 */

public interface RoomManagement
{
    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<JsonObject> loginRes(@Body JsonObject jsonObject);


    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<RegUser> Regres(@Body JsonObject jsonObject);

    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<InsertPojo> insersavings(@Body JsonObject jsonObject);

    @Headers("Content-Type:application/json")
    @POST("api.php")
    Call<AddSavings> addsaving(@Body JsonObject jsonObject);
}
