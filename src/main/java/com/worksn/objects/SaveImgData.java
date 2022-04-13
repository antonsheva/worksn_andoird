package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveImgData {
    @SerializedName(C_.STR_IMG)
    @Expose
    private String img;

    @SerializedName(C_.STR_IMG_ICON)
    @Expose
    private String imgIcon;

    @SerializedName(C_.STR_CREATE_ID)
    @Expose
    private String createId;

    @SerializedName(C_.STR_MSG_ID)
    @Expose
    private Long msgId;

    @SerializedName(C_.STR_CONSUMER_ID)
    @Expose
    private Integer destinationConsumerId;

    public Integer getDestinationConsumerId() {
        return destinationConsumerId;
    }

    public void setDestinationConsumerId(Integer destinationConsumerId) {
        this.destinationConsumerId = destinationConsumerId;
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
