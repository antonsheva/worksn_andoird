package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssocData {
    @SerializedName("name")
    @Expose
    private String name = null;
    @SerializedName("val")
    @Expose
    private Object val = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }
}
