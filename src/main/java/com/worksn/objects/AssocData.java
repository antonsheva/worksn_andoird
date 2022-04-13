package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssocData {
    @SerializedName(C_.STR_NAME)
    @Expose
    private String name = null;
    @SerializedName(C_.STR_VAL)
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
