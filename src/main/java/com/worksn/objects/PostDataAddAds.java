package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostDataAddAds {
    @SerializedName("active")
    @Expose
    private Integer active = 1;
    @SerializedName("ads_type")
    @Expose
    private Integer adsType = null;
    @SerializedName("category")
    @Expose
    private Integer category = null;
    @SerializedName("coord_x")
    @Expose
    private Double coordX = null;
    @SerializedName("coord_y")
    @Expose
    private Double coordY = null;
    @SerializedName("cost")
    @Expose
    private Integer cost = null;
    @SerializedName("description")
    @Expose
    private String description = null;
    @SerializedName("img")
    @Expose
    private String img = null;
    @SerializedName("lifetime")
    @Expose
    private Long lifetime = null;

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getAdsType() {
        return adsType;
    }

    public void setAdsType(Integer adsType) {
        this.adsType = adsType;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getLifetime() {
        return lifetime;
    }

    public void setLifetime(Long lifetime) {
        this.lifetime = lifetime;
    }
}
