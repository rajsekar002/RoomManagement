package com.androindian.raj.roommanagement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Raj on 4/26/2018.
 */

public class InsertPojo {
    @SerializedName("data")
    @Expose
    private List<InsertPojoarray> data = null;
    @SerializedName("response")
    @Expose
    private String response;

    public List<InsertPojoarray> getData() {
        return data;
    }

    public void setData(List<InsertPojoarray> data) {
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}