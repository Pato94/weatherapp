package com.contreras.cristian.weatherappcristiancontreras.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.contreras.cristian.weatherappcristiancontreras.R;
import com.contreras.cristian.weatherappcristiancontreras.adapter.WeatherAdapter;
import com.contreras.cristian.weatherappcristiancontreras.api.WeatherResponse;
import com.contreras.cristian.weatherappcristiancontreras.api.WeatherService;
import com.contreras.cristian.weatherappcristiancontreras.model.WeatherData;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Pato on 10/8/2015.
 */
public class WeekWeatherListFragment extends Fragment {

    @Bind(R.id.recyclerViewWeekWeather)
    RecyclerView recyclerViewWeekWeather;
    private ArrayList<WeatherData> mWeatherDataList;
    private WeatherAdapter mAdapter;
    private WeatherService.WeatherAPI api;

    public static WeekWeatherListFragment newInstance() {
        Bundle args = new Bundle();
        WeekWeatherListFragment fragment = new WeekWeatherListFragment();

        fragment.setArguments(args);

        return fragment;
    }

    public WeekWeatherListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_week_weather_list, container, false);
        ButterKnife.bind(this, rootView);

        recyclerViewWeekWeather.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewWeekWeather.setHasFixedSize(true);

        mWeatherDataList = new ArrayList<>();

        mAdapter = new WeatherAdapter(mWeatherDataList);
        recyclerViewWeekWeather.setAdapter(mAdapter);

        api = WeatherService.getInstance().getAPI();
        requestWeatherData();
        return rootView;
    }

    private void requestWeatherData() {
        api.getWeatherFor("Buenos Aires", "json", "metric", WeatherService.getApiKey())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherResponse>() {
            @Override
            public void onCompleted() {
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(WeatherResponse weatherResponse) {
                mWeatherDataList.addAll(weatherResponse.list);
            }
        });
    }
}
