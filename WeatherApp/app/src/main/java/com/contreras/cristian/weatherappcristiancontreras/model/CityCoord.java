package com.contreras.cristian.weatherappcristiancontreras.model;

import org.parceler.Parcel;

/**
 * Created by Pato on 10/10/2015.
 */
@Parcel
public class CityCoord {
    private float lon;
    private float lat;

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
