package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserReview {
    @SerializedName("id")
    @Expose
    private Long id;


    @SerializedName("sender_id")
    @Expose
    private Integer sender_id;


    @SerializedName("consumer_id")
    @Expose
    private Integer consumer_id;


    @SerializedName("star_qt")
    @Expose
    private Integer star_qt;


    @SerializedName("favorite")
    @Expose
    private Integer favorite;


    @SerializedName("comment")
    @Expose
    private String comment = null;


    @SerializedName("create_date")
    @Expose
    private String create_date;

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("img_icon")
    @Expose
    private String imgIcon;


    public Long getId() {
        return id;
    }

    public Integer getSender_id() {
        return sender_id;
    }

    public Integer getConsumer_id() {
        return consumer_id;
    }

    public Integer getStar_qt() {
        return star_qt;
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

    public String getCreate_date() {
        return create_date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSender_id(Integer sender_id) {
        this.sender_id = sender_id;
    }

    public void setConsumer_id(Integer consumer_id) {
        this.consumer_id = consumer_id;
    }

    public void setStar_qt(Integer star_qt) {
        this.star_qt = star_qt;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
}
