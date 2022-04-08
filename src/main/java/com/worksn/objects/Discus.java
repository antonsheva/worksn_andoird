package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Discus {
    public void setId(Long id) {
        this.id = id;
    }

    public void setSpeaker_1(Integer speaker_1) {
        this.speaker_1 = speaker_1;
    }

    public void setSpeaker_2(Integer speaker_2) {
        this.speaker_2 = speaker_2;
    }

    public void setAds_id(Long ads_id) {
        this.ads_id = ads_id;
    }

    public Long getId() {
        return id;
    }

    public Integer getSpeaker_1() {
        return speaker_1;
    }

    public Integer getSpeaker_2() {
        return speaker_2;
    }

    public Long getAds_id() {
        return ads_id;
    }

    @SerializedName("id")
    @Expose
    Long id = null;
    @SerializedName("speaker_1")
    @Expose
    Integer speaker_1 = null;
    @SerializedName("speaker_2")
    @Expose
    Integer speaker_2 = null;
    @SerializedName("ads_id")
    @Expose
    Long ads_id = null;






}
