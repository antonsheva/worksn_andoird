package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserReview {
    @SerializedName(C_.STR_ID)
    @Expose
    private Long id;


    @SerializedName(C_.STR_SENDER_ID)
    @Expose
    private Integer senderId;


    @SerializedName(C_.STR_CONSUMER_ID)
    @Expose
    private Integer consumerId;


    @SerializedName(C_.STR_STAR_QT)
    @Expose
    private Integer starQt;


    @SerializedName(C_.STR_FAVORITE)
    @Expose
    private Integer favorite;


    @SerializedName(C_.STR_COMMENT)
    @Expose
    private String comment = null;


    @SerializedName(C_.STR_CREATE_DATE)
    @Expose
    private String createDate;

    @SerializedName(C_.STR_IMG)
    @Expose
    private String img;

    @SerializedName(C_.STR_IMG_ICON)
    @Expose
    private String imgIcon;


    public Long getId() {
        return id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public Integer getStarQt() {
        return starQt;
    }

    public Integer getFavorite() {
        return favorite;
    }

    public String getComment() {
        return comment;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public void setStarQt(Integer starQt) {
        this.starQt = starQt;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
