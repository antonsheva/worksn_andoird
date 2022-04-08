package com.worksn.websocket.init_ssl;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NetResponse {

    @SerializedName("error")
    @Expose
    private Integer error;


    @SerializedName("response")
    @Expose
    private String response;


    @SerializedName("result")
    @Expose
    private Integer result;

    @SerializedName("data")
    @Expose
    private String data;

    @SerializedName("act")
    @Expose
    String act = null;

    public String getAct() {
        return act;
    }

    public Integer getError() {
        return error;
    }
    public String getResponse() {
        return response;
    }
    public Integer getResult() {
        return result;
    }
    public String getData() {return data;}

}