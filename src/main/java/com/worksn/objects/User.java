package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName(C_.STR_BAN_LIST)
    @Expose
    private String banList = null;

    @SerializedName(C_.STR_LIKE_LIST)
    @Expose
    private String likeList = null;

    @SerializedName(C_.STR_ID)
    @Expose
    private Integer id = 0;

    @SerializedName(C_.STR_LOGIN)
    @Expose
    private String login = null;

    @SerializedName(C_.STR_AUTO_AUTH)
    @Expose
    private Integer autoAuth = 0;

    @SerializedName(C_.STR_NAME)
    @Expose
    private String name = null;

    @SerializedName(C_.STR_S_NAME)
    @Expose
    private String sName = null;

    @SerializedName(C_.STR_PHONE)
    @Expose
    private String phone = null;

    @SerializedName(C_.STR_EMAIL)
    @Expose
    private String email = null;

    @SerializedName(C_.STR_IMG)
    @Expose
    private String img = null;

    @SerializedName(C_.STR_IMG_ICON)
    @Expose
    private String imgIcon = null;

    @SerializedName(C_.STR_CREATE_DATE)
    @Expose
    private String createDate = null;

    @SerializedName(C_.STR_LAST_TIME)
    @Expose
    private String lastTime = null;

    @SerializedName(C_.STR_RATING)
    @Expose
    private Float rating = 0f;

    @SerializedName(C_.STR_RIGHTS)
    @Expose
    private Integer rights = null;

    @SerializedName(C_.STR_VOTE_QT)
    @Expose
    private Integer voteQt = null;

    @SerializedName(C_.STR_WEB_SITE)
    @Expose
    private String webSite = null;

    @SerializedName(C_.STR_ABOUT_USER)
    @Expose
    private String aboutUser = null;

    @SerializedName(C_.STR_WS_TOKEN)
    @Expose
    private String wsToken = null;

    private Boolean online = false;

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
