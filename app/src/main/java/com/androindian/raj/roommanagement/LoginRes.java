package com.androindian.raj.roommanagement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Raj on 4/21/2018.
 */

public class LoginRes {
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("data")
    @Expose
    private LoginArrayRes data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public LoginArrayRes getData() {
        return data;
    }

    public void setData(LoginArrayRes data) {
        this.data = data;
    }

}
