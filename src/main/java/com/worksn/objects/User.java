package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("notify_id")
    @Expose
    private Integer notifyId;

    @SerializedName("system_notify")
    @Expose
    private String systemNotify;

    @SerializedName(C_.VAR_BAN_LIST)
    @Expose
    private String banList = null;

    @SerializedName(C_.VAR_LIKE_LIST)
    @Expose
    private String likeList = null;

    @SerializedName("id")
    @Expose
    private Integer id = 0;

    @SerializedName(C_.VAR_APP_ID)
    @Expose
    private String applicationId;

    @SerializedName("login")
    @Expose
    private String login = null;

    @SerializedName("auto_auth")
    @Expose
    private Integer autoAuth = 0;

    @SerializedName("name")
    @Expose
    private String name = null;

    @SerializedName("s_name")
    @Expose
    private String sName = null;

    @SerializedName("phone")
    @Expose
    private String phone = null;

    @SerializedName("email")
    @Expose
    private String email = null;

    @SerializedName("img")
    @Expose
    private String img = null;

    @SerializedName("img_icon")
    @Expose
    private String imgIcon = null;

    @SerializedName("create_date")
    @Expose
    private String createDate = null;

    @SerializedName("last_time")
    @Expose
    private String lastTime = null;

    @SerializedName("rating")
    @Expose
    private Float rating = 0f;

    @SerializedName("rights")
    @Expose
    private Integer rights = null;

    @SerializedName("vote_qt")
    @Expose
    private Integer voteQt = null;

    @SerializedName("web_site")
    @Expose
    private String webSite = null;

    @SerializedName("about_user")
    @Expose
    private String aboutUser = null;

    @SerializedName("ws_token")
    @Expose
    private String wsToken = null;

    private Boolean online = false;



    public Integer getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Integer notifyId) {
        this.notifyId = notifyId;
    }



    public void setSystemNotify(String systemNotify) {
        this.systemNotify = systemNotify;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getBanList() {
        return banList;
    }

    public void setBanList(String banList) {
        this.banList = banList;
    }

    public String getLikeList() {
        return likeList;
    }

    public void setLikeList(String likeList) {
        this.likeList = likeList;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getAutoAuth() {
        return autoAuth;
    }

    public void setAutoAuth(Integer autoAuth) {
        this.autoAuth = autoAuth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getRights() {
        return rights;
    }

    public void setRights(Integer rights) {
        this.rights = rights;
    }

    public Integer getVoteQt() {
        return voteQt;
    }

    public void setVoteQt(Integer voteQt) {
        this.voteQt = voteQt;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getAboutUser() {
        return aboutUser;
    }

    public void setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
    }

    public String getWsToken() {
        return wsToken;
    }

    public void setWsToken(String wsToken) {
        this.wsToken = wsToken;
    }

    public String getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(String imgIcon) {
        this.imgIcon = imgIcon;
    }

}
