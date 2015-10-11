package com.contreras.cristian.weatherappcristiancontreras.api;

import com.contreras.cristian.weatherappcristiancontreras.model.City;
import com.contreras.cristian.weatherappcristiancontreras.model.DayWeather;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by Pato on 09/10/2015.
 */
@Parcel
public class WeatherResponse {
    private City city;
    private int cod;
    private List<DayWeather> list;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public List<DayWeather> getList() {
        return list;
    }

    public void setList(List<DayWeather> list) {
        this.list = list;
    }
}
