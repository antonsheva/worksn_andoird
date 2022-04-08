package com.worksn.websocket.init_ssl;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RequestData {
    @SerializedName("act")
    @Expose
    private String act;

    @SerializedName("user_id")
    @Expose
    private Integer user_id;

    @SerializedName("s_token")
    @Expose
    private String s_token;

    @SerializedName("data_group")
    @Expose
    private Object data_group = null;

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getS_token() {
        return s_token;
    }

    public void setS_token(String s_token) {
        this.s_token = s_token;
    }

    public Object getData_group() {
        return data_group;
    }

    public void setData_group(Object data_group) {
        this.data_group = data_group;
    }

    public RequestData(String act){
        this.act     = act;
    }
}