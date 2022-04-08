package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveImgData {
    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("img_icon")
    @Expose
    private String imgIcon;

    @SerializedName("create_id")
    @Expose
    private String createId;

    @SerializedName("msg_id")
    @Expose
    private Long msgId;

    @SerializedName("consumer_id")
    @Expose
    private Integer consumerId;

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
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

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }
}
