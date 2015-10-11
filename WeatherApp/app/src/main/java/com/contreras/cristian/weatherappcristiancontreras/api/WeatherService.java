package com.contreras.cristian.weatherappcristiancontreras.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Pato on 10/8/2015.
 */
public class WeatherService {
    private static WeatherService mInstance;
    private final WeatherAPI mAPI;
    private final static String ENDPOINT = "http://api.openweathermap.org/data/2.5/forecast";
    private final static String APIKEY = "ad30441ec6bf6e2dbb501be9c6a814c1";

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

    public WeatherAPI getAPI() {
        return getInstance().mAPI;
    }

    public static WeatherService getInstance() {
        if (mInstance == null)
            mInstance = new WeatherService(ENDPOINT);
        return mInstance;
    }

    public static String getApiKey() {
        return APIKEY;
    }
    public interface WeatherAPI {
        @GET("/daily")
        Observable<WeatherResponse> getWeatherFor(@Query("q") String cityName, @Query("mode") String mode, @Query("units") String units, @Query("appid") String apiKey);
    }
}
