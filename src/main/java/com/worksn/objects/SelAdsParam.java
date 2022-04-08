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


    public SelAdsParam(){}
    public SelAdsParam(AdsCllct adsCllct, MapAreaCoords mapAreaCoords){
        this.adsCllct   = adsCllct;
        this.mapAreaCoords = mapAreaCoords;
    }


    public AdsCllct getAdsCllct() {
        return adsCllct;
    }

    public void setAdsCllct(AdsCllct adsCllct) {
        this.adsCllct = adsCllct;
    }

    public MapAreaCoords getMapAreaCoords() {
        return mapAreaCoords;
    }

    public void setMapAreaCoords(MapAreaCoords mapAreaCoords) {
        this.mapAreaCoords = mapAreaCoords;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }
}
