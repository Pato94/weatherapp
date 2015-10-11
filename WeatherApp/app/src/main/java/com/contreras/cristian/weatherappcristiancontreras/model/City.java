package com.contreras.cristian.weatherappcristiancontreras.model;

import org.parceler.Parcel;

/**
 * Created by Pato on 09/10/2015.
 */
@Parcel
public class City {
    private long id;
    private String name;
    private CityCoord coord;
    private String country;
    private int population;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityCoord getCoord() {
        return coord;
    }

    public void setCoord(CityCoord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
