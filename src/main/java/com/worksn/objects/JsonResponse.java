package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonResponse {

    @SerializedName(C_.STR_ERROR)
    @Expose
    private Integer error;


    @SerializedName(C_.STR_RESPONSE)
    @Expose
    private String response;


    @SerializedName(C_.STR_RESULT)
    @Expose
    private Integer result;

    @SerializedName(C_.STR_DATA)
    @Expose
    private String data;

    @SerializedName(C_.STR_CONTEXT)
    @Expose
    private MyContext context;


    @SerializedName(C_.STR_ACT)
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