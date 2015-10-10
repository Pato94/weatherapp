package com.contreras.cristian.weatherappcristiancontreras.api;

import com.contreras.cristian.weatherappcristiancontreras.model.City;
import com.contreras.cristian.weatherappcristiancontreras.model.WeatherData;

import java.util.List;

/**
 * Created by Karen on 09/10/2015.
 */
public class WeatherResponse {
    public City city;
    public int cod;
    public List<WeatherData> list;
}
