package com.worksn.websocket.init_ssl;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.worksn.objects.C_;


public class RequestData {
    @SerializedName(C_.STR_ACT)
    @Expose
    private String act;

    @SerializedName(C_.STR_USER_ID)
    @Expose
    private Integer userId;

    @SerializedName(C_.STR_WS_TOKEN)
    @Expose
    private String wsToken;

    @SerializedName(C_.STR_DATA_GROUP)
    @Expose
    private Object dataGroup = null;

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getWsToken() {
        return wsToken;
    }

    public void setWsToken(String wsToken) {
        this.wsToken = wsToken;
    }

    public Object getDataGroup() {
        return dataGroup;
    }

    public void setDataGroup(Object dataGroup) {
        this.dataGroup = dataGroup;
    }

    public RequestData(String act){
        this.act     = act;
    }
}