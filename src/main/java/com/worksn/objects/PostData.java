package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.worksn.singleton.MyStorage;
import com.worksn.singleton.Usr;

public class PostData {
    @SerializedName("act")
    @Expose
    private String act;

    @SerializedName(C_.VAR_USER_ID)
    @Expose
    private Integer userId;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @SerializedName(C_.VAR_APP_ID)
    @Expose
    private String applicationId;

    @SerializedName(C_.VAR_S_TOKEN)
    @Expose
    private String token;

    @SerializedName(C_.VAR_DATA_GROUP)
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getDataGroup() {
        return dataGroup;
    }

    public void setDataGroup(Object dataGroup) {
        this.dataGroup = dataGroup;
    }

    public PostData(String act, Object dataGroup){
        this.act     = act;
        if (Usr.i().getUser() != null) {
            this.applicationId = MyStorage.i().getApplicationId();
            this.token = Usr.i().getUser().getWsToken();
            this.userId = Usr.i().getUser().getId();
        }
        if (dataGroup != null) this.dataGroup = dataGroup;
    }
}