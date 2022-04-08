package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonResponse {

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

    @SerializedName("context")
    @Expose
    private MyContext context;


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
    public MyContext getContext() {
        return context;
    }
}