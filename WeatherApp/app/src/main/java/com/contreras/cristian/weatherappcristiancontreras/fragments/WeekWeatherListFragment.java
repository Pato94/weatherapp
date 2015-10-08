package com.contreras.cristian.weatherappcristiancontreras.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.contreras.cristian.weatherappcristiancontreras.R;
import com.contreras.cristian.weatherappcristiancontreras.WeatherApp;
import com.contreras.cristian.weatherappcristiancontreras.adapter.WeatherAdapter;
import com.contreras.cristian.weatherappcristiancontreras.model.WeatherData;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pato on 10/8/2015.
 */
public class WeekWeatherListFragment extends Fragment {

    private static final String ARG_ENTITY_ID = "entity_id";

    @Bind(R.id.recyclerViewWeekWeather)
    RecyclerView recyclerViewWeekWeather;
    private ArrayList<WeatherData> mWeatherDataList;
    private WeatherAdapter mAdapter;

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

        requestWeatherData();
        return rootView;
    }

    private void requestWeatherData() {

    }
}
