package com.worksn.websocket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.worksn.objects.C_;
import com.worksn.singleton.MyStorage;
import com.worksn.singleton.Usr;

public class WsSendData {
    public WsSendData(String act){
        addServiceData(act);
    }
    public WsSendData(String act, Object dataGroup){
        addServiceData(act);
        if(dataGroup != null)data_group = dataGroup;
    }
    private void addServiceData(String act){
        this.act = act;
        try {
            if (Ws.getUser() == null)return;
            ws_token = Ws.getUser().getWsToken();
            user_id  = Ws.getUser().getId();
            user_login = Ws.getUser().getLogin();
            applicationId = MyStorage.i().getApplicationId();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }



    @SerializedName(C_.VAR_SHOW_STATUS)
    @Expose
    private Integer showStatus = null;

    @SerializedName("app_id")
    @Expose
    private String applicationId;
    @SerializedName("id_list")
    @Expose
    private String idList;

    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;

    @SerializedName("act")
    @Expose
    String act = null;

    @SerializedName("user_id")
    @Expose
    Integer user_id = null;

    @SerializedName("user_login")
    @Expose
    String user_login = null;

    @SerializedName("user_img")
    @Expose
    String user_img = null;

    @SerializedName("ws_token")
    @Expose
    String ws_token = null;

    @SerializedName("data_group")
    @Expose
    private Object data_group = null;


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

    public Object getData_group() {
        return data_group;
    }

    public void setData_group(Object data_group) {
        this.data_group = data_group;
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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getWs_token() {
        return ws_token;
    }

    public void setWs_token(String ws_token) {
        this.ws_token = ws_token;
    }
}
