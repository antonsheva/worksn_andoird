package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelAdsParam {
    @SerializedName("cllct")
    @Expose
    private AdsCllct adsCllct;
    @SerializedName("crd")
    @Expose
    private MapAreaCoords mapAreaCoords;
    @SerializedName("search_phrase")
    @Expose
    private String searchPhrase;

    public SelAdsParam(AdsCllct adsCllct, MapAreaCoords mapAreaCoords){
        this.adsCllct   = adsCllct;
        this.mapAreaCoords = mapAreaCoords;
    }
}
