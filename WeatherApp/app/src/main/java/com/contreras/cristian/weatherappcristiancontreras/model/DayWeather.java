package com.contreras.cristian.weatherappcristiancontreras.model;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by Pato on 10/8/2015.
 */
@Parcel
public class DayWeather {
    public long dt;
    public float pressure;
    public int humidity;
    public float speed;
    public int deg;
    public int clouds;
    public List<WeatherData> weather;
    public TempData temp;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public List<WeatherData> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherData> weatherDataList) {
        this.weather = weatherDataList;
    }

    public TempData getTemp() {
        return temp;
    }

    public void setTemp(TempData temp) {
        this.temp = temp;
    }
}
