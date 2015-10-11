package com.contreras.cristian.weatherappcristiancontreras.model;

import org.parceler.Parcel;

/**
 * Created by Pato on 10/10/2015.
 */
@Parcel
public class WeatherData {
    public int id;
    public String main;
    public String description;
    public String icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
