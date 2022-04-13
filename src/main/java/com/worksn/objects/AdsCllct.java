package com.worksn.objects;

import android.app.Activity;
import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.worksn.R;

public class AdsCllct {
    @SerializedName(C_.STR_ADS_TYPE)
    @Expose
    private Integer adsType = 0;
    @SerializedName(C_.STR_CATEGORY)
    @Expose
    private Integer catNum;
    @SerializedName(C_.STR_USER_ID)
    @Expose
    private Integer userId;
    @SerializedName(C_.STR_REMOVE)
    @Expose
    private Integer remove = 0;
    @SerializedName(C_.STR_SEARCH_PHRASE)
    @Expose
    private String searchPhrase;
    @SerializedName(C_.STR_ACTIVE)
    @Expose
    private Integer active = 1;

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    private String catName = "Все категории";



    public void clear(Context context){
        androidx.appcompat.widget.AppCompatButton mName =
                ((Activity)context).findViewById(R.id.btAdsParamCategory);

        catName = "Все категории";
        adsType = 0;
        catNum = null;
        userId = null;
        remove = null;
        active = 1;
        searchPhrase = null;
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mName.setText(catName);
            }
        });
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }



    public Integer getAdsType() {
        return adsType;
    }

    public void setAdsType(Integer adsType) {
        this.adsType = adsType;
    }

    public Integer getCatNum() {
        return catNum;
    }

    public void setCatNum(Integer catNum) {
        this.catNum = catNum;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRemove() {
        return remove;
    }

    public void setRemove(Integer remove) {
        this.remove = remove;
    }
}
