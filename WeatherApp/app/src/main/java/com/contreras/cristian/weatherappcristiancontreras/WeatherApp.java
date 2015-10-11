package com.contreras.cristian.weatherappcristiancontreras;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Pato on 10/8/2015.
 */
public class WeatherApp extends Application {

    private static WeatherApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mInstance = this;
    }

    public static WeatherApp getInstance() {
        return mInstance;
    }
}
