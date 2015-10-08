package com.contreras.cristian.weatherappcristiancontreras.api;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;

/**
 * Created by Pato on 10/8/2015.
 */
public class WeatherService {
    private static WeatherService mInstance;
    private final WeatherAPI mAPI;
    private final static String ENDPOINT = "";

    public WeatherService(String restUrl) {

        Gson gsonConfig = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        RestAdapter mAdapter = new RestAdapter.Builder()
                .setEndpoint(restUrl)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gsonConfig))
                .build();

        mAPI = mAdapter.create(WeatherAPI.class);
    }

    public static WeatherAPI getAPI() {
        return getInstance().mAPI;
    }

    public static WeatherService getInstance() {
        if (mInstance == null)
            mInstance = new WeatherService(ENDPOINT);
        return mInstance;
    }

    public interface WeatherAPI {
        @GET("")
        public void getWeatherFor();
    }
}
