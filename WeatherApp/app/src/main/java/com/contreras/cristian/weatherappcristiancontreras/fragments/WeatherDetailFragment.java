package com.contreras.cristian.weatherappcristiancontreras.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.contreras.cristian.weatherappcristiancontreras.R;
import com.contreras.cristian.weatherappcristiancontreras.model.DayWeather;
import com.contreras.cristian.weatherappcristiancontreras.model.WeatherData;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.contreras.cristian.weatherappcristiancontreras.Utils.getFormattedDateFromTimeInSeconds;
import static com.contreras.cristian.weatherappcristiancontreras.Utils.getResourceFromCode;
import static com.contreras.cristian.weatherappcristiancontreras.Utils.getStringTempFromFloat;

/**
 * Created by Pato on 10/8/2015.
 */
public class WeatherDetailFragment extends Fragment {

    @Bind(R.id.textDate) TextView textDate;
    @Bind(R.id.imageWeather) ImageView imageWeather;
    @Bind(R.id.textTempMax) TextView textTempMax;
    @Bind(R.id.textTempMin) TextView textTempMin;
    @Bind(R.id.textTempAvg) TextView textTempAvg;
    @Bind(R.id.textDescription) TextView textDescription;

    public static final String WEATHER_DATA_KEY = "WEATHER_DATA";
    private DayWeather mWeatherData;

    public static WeatherDetailFragment newInstance(DayWeather dayWeather) {
        Bundle args = new Bundle();
        WeatherDetailFragment fragment = new WeatherDetailFragment();

        args.putParcelable(WEATHER_DATA_KEY, Parcels.wrap(dayWeather));
        fragment.setArguments(args);

        return fragment;
    }

    public WeatherDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mWeatherData = Parcels.unwrap(arguments.getParcelable(WEATHER_DATA_KEY));
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_detail, container, false);
        ButterKnife.bind(this, rootView);

        if (mWeatherData != null) {
            textDate.setText(getFormattedDateFromTimeInSeconds(mWeatherData.getDt()));

            float max = mWeatherData.getTemp().getMax();
            float min = mWeatherData.getTemp().getMin();
            float avg = Math.round((max + min)/ 2);

            textTempMax.setText(getStringTempFromFloat(max));
            textTempMin.setText(getStringTempFromFloat(min));
            textTempAvg.setText(String.format("~%s", getStringTempFromFloat(avg)));

            if (mWeatherData.getWeather() != null && mWeatherData.getWeather().size() > 0) {
                WeatherData weatherData = mWeatherData.getWeather().get(0);

                imageWeather.setImageResource(getResourceFromCode(weatherData.getIcon()));
                textDescription.setText(String.format("%s: %s", weatherData.getMain(), weatherData.getDescription()));
            }
        }

        return rootView;
    }
}
