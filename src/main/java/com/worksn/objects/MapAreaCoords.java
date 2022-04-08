package com.worksn.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.yandex.mapkit.map.VisibleRegion;

public class MapAreaCoords {
    @SerializedName("min_x")
    @Expose
    private Double minX;
    @SerializedName("min_y")
    @Expose
    private Double minY;
    @SerializedName("max_x")
    @Expose
    private Double maxX;
    @SerializedName("max_y")
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

