package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ads {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("ads_type")
    @Expose
    private Integer adsType;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("user_login")
    @Expose
    private String userLogin;
    @SerializedName("user_rating")
    @Expose
    private Float userRating;
    @SerializedName("user_vote_qt")
    @Expose
    private Integer userVoteQt;

    @SerializedName("user_img")
    @Expose
    private String userImg;
    @SerializedName("user_img_icon")
    @Expose
    private String userImgIcon;
    @SerializedName("user_online")
    @Expose
    private boolean userOnline = false;

    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("coord_x")
    @Expose
    private Double coordX;
    @SerializedName("coord_y")
    @Expose
    private Double coordY;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("img_icon")
    @Expose
    private String imgIcon;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cost")
    @Expose
    private Integer cost = 0;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("create_time")
    @Expose
    private Long createTime;
    @SerializedName("lifetime")
    @Expose
    private Long lifetime;
    @SerializedName("remove")
    @Expose
    private Integer remove;
    @SerializedName("min_x")
    @Expose
    private Float minX;
    @SerializedName("max_x")
    @Expose
    private Float maxX;
    @SerializedName("min_y")
    @Expose
    private Float minY;
    @SerializedName("max_y")
    @Expose
    private Float maxY;

    @SerializedName(C_.VAR_HOUR_START)
    @Expose
    private Integer hourStart;
    @SerializedName(C_.VAR_HOUR_STOP)
    @Expose
    private Integer hourStop;
    @SerializedName(C_.VAR_MIN_START)
    @Expose
    private Integer minStart;
    @SerializedName(C_.VAR_VISIBLE_MODE)
    @Expose
    private Integer visibleMode;

    @SerializedName(C_.VAR_EDIT)
    @Expose
    private Integer edit;


    public Integer getEdit() {
        return edit;
    }

    public void setEdit(Integer edit) {
        this.edit = edit;
    }



    public Integer getVisibleMode() {
        return visibleMode;
    }

    public void setVisibleMode(Integer visibleMode) {
        this.visibleMode = visibleMode;
    }

    public Integer getHourStart() {
        return hourStart;
    }

    public void setHourStart(Integer hourStart) {
        this.hourStart = hourStart;
    }

    public Integer getHourStop() {
        return hourStop;
    }

    public void setHourStop(Integer hourStop) {
        this.hourStop = hourStop;
    }

    public Integer getMinStart() {
        return minStart;
    }

    public void setMinStart(Integer minStart) {
        this.minStart = minStart;
    }

    public Integer getMinStop() {
        return minStop;
    }

    public void setMinStop(Integer minStop) {
        this.minStop = minStop;
    }

    @SerializedName(C_.VAR_MIN_STOP)
    @Expose
    private Integer minStop;


    public boolean isUserOnline() {
        return userOnline;
    }

    public void setUserOnline(boolean userOnline) {
        this.userOnline = userOnline;
    }

    public Integer getUserVoteQt() {
        return userVoteQt;
    }

    public void setUserVoteQt(Integer userVoteQt) {
        this.userVoteQt = userVoteQt;
    }

    public String getUserImgIcon() {
        return userImgIcon;
    }

    public void setUserImgIcon(String userImgIcon) {
        this.userImgIcon = userImgIcon;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAdsType() {
        return adsType;
    }

    public void setAdsType(Integer adsType) {
        this.adsType = adsType;
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

    public Float getUserRating() {
        return userRating;
    }

    public void setUserRating(Float userRating) {
        this.userRating = userRating;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Double getCoordX() {
        return coordX;
    }

    public void setCoordX(Double coordX) {
        this.coordX = coordX;
    }

    public Double getCoordY() {
        return coordY;
    }

    public void setCoordY(Double coordY) {
        this.coordY = coordY;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(String imgIcon) {
        this.imgIcon = imgIcon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLifetime() {
        return lifetime;
    }

    public void setLifetime(Long lifetime) {
        this.lifetime = lifetime;
    }

    public Integer getRemove() {
        return remove;
    }

    public void setRemove(Integer remove) {
        this.remove = remove;
    }

    public Float getMinX() {
        return minX;
    }

    public void setMinX(Float minX) {
        this.minX = minX;
    }

    public Float getMaxX() {
        return maxX;
    }

    public void setMaxX(Float maxX) {
        this.maxX = maxX;
    }

    public Float getMinY() {
        return minY;
    }

    public void setMinY(Float minY) {
        this.minY = minY;
    }

    public Float getMaxY() {
        return maxY;
    }

    public void setMaxY(Float maxY) {
        this.maxY = maxY;
    }


    private Integer viewPosition;

    public Integer getViewPosition() {
        return viewPosition;
    }

    public void setViewPosition(Integer viewPosition) {
        this.viewPosition = viewPosition;
    }
}