package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StructTxtData {
    @SerializedName(C_.STR_CREATE_DATE)
    @Expose
    private String createDate = null;
    @SerializedName(C_.STR_NAME)
    @Expose
    private String name = null;
    @SerializedName(C_.STR_DESCRIPTION)
    @Expose
    private String description;


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
