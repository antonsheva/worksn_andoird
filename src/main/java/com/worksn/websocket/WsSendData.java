package com.worksn.websocket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.worksn.objects.C_;
import com.worksn.singleton.MyStorage;

public class WsSendData {
    public WsSendData(String act){
        addServiceData(act);
    }
    public WsSendData(String act, Object dataGroup){
        addServiceData(act);
        if(dataGroup != null) this.dataGroup = dataGroup;
    }
    private void addServiceData(String act){
        this.act = act;
        try {
            if (Ws.getUser() == null)return;
            wsToken = Ws.getUser().getWsToken();
            userId = Ws.getUser().getId();
            userLogin = Ws.getUser().getLogin();
            applicationId = MyStorage.i().getApplicationId();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }



    @SerializedName(C_.STR_SHOW_STATUS)
    @Expose
    private Integer showStatus = null;

    @SerializedName(C_.STR_APP_ID)
    @Expose
    private String applicationId;
    @SerializedName(C_.STR_ID_LIST)
    @Expose
    private String idList;

    @SerializedName(C_.STR_OWNER_ID)
    @Expose
    private Integer ownerId;

    @SerializedName(C_.STR_ACT)
    @Expose
    String act = null;

    @SerializedName(C_.STR_USER_ID)
    @Expose
    Integer userId = null;

    @SerializedName(C_.STR_USER_LOGIN)
    @Expose
    String userLogin = null;

    @SerializedName(C_.STR_USER_IMG)
    @Expose
    String userImg = null;

    @SerializedName(C_.STR_WS_TOKEN)
    @Expose
    String wsToken = null;

    @SerializedName(C_.STR_DATA_GROUP)
    @Expose
    private Object dataGroup = null;


    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }

    public Object getDataGroup() {
        return dataGroup;
    }

    public void setDataGroup(Object dataGroup) {
        this.dataGroup = dataGroup;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getWsToken() {
        return wsToken;
    }

    public void setWsToken(String wsToken) {
        this.wsToken = wsToken;
    }
}
