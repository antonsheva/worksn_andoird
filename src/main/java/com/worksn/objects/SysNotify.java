package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SysNotify {
    @SerializedName(C_.STR_ID)
    @Expose
    private Long id = null;
    @SerializedName(C_.STR_USER_ID)
    @Expose
    private Integer userId = null;
    @SerializedName(C_.STR_CREATE_DATE)
    @Expose
    private String createDate = null;
    @SerializedName(C_.STR_CONTENT)
    @Expose
    private String content = null;
    @SerializedName(C_.STR_IMG)
    @Expose
    private String img = null;

    @SerializedName(C_.STR_TYPE)
    @Expose
    private Integer type = 0;

    @SerializedName(C_.STR_VIEW)
    @Expose
    private Integer view = null;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
