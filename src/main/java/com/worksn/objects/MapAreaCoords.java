package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.yandex.mapkit.map.VisibleRegion;

public class MapAreaCoords {
    @SerializedName(C_.STR_MIN_X)
    @Expose
    private Double minX;
    @SerializedName(C_.STR_MIN_Y)
    @Expose
    private Double minY;
    @SerializedName(C_.STR_MAX_X)
    @Expose
    private Double maxX;
    @SerializedName(C_.STR_MAX_Y)
    @Expose
    private Double maxY;


    public MapAreaCoords(){};
    public MapAreaCoords(VisibleRegion visibleRegion){
        maxX = visibleRegion.getTopLeft().getLatitude();
        minX = visibleRegion.getBottomRight().getLatitude();
        minY = visibleRegion.getTopLeft().getLongitude();
        maxY = visibleRegion.getBottomRight().getLongitude();
    }


    public Double getMinX() {
        return minX;
    }

    public void setMinX(Double minX) {
        this.minX = minX;
    }

    public Double getMinY() {
        return minY;
    }

    public void setMinY(Double minY) {
        this.minY = minY;
    }

    public Double getMaxX() {
        return maxX;
    }

    public void setMaxX(Double maxX) {
        this.maxX = maxX;
    }

    public Double getMaxY() {
        return maxY;
    }

    public void setMaxY(Double maxY) {
        this.maxY = maxY;
    }


}

