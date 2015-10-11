package com.contreras.cristian.weatherappcristiancontreras.fragments;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.contreras.cristian.weatherappcristiancontreras.R;
import com.contreras.cristian.weatherappcristiancontreras.adapter.WeatherAdapter;
import com.contreras.cristian.weatherappcristiancontreras.api.WeatherResponse;
import com.contreras.cristian.weatherappcristiancontreras.api.WeatherService;
import com.contreras.cristian.weatherappcristiancontreras.model.DayWeather;
import com.facebook.drawee.view.SimpleDraweeView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Pato on 10/8/2015.
 */
public class WeekWeatherListFragment extends Fragment {

    @Bind(R.id.recyclerViewWeekWeather) RecyclerView recyclerViewWeekWeather;
    @Bind(R.id.textCityName) TextView textCityName;
    @Bind(R.id.textLatAndLon) TextView textLatAndLon;
    @Bind(R.id.imageCountryFlag) SimpleDraweeView imageCountryFlag;
    @Bind(R.id.swipeLayout) SwipeRefreshLayout swipeLayout;

    private static final String DATA_KEY = "LAST_WEATHER_RESPONSE";
    private WeatherResponse mLastWeatherResponse;
    private ArrayList<DayWeather> mDayWeatherList;
    private WeatherAdapter mAdapter;
    private WeatherService.WeatherAPI api;
    private FragmentInteraction mInteractionListener;
    private boolean shouldUpdate;

    private final WeatherAdapter.DayClickListener mDayClickListener = new WeatherAdapter.DayClickListener() {
        @Override
        public void onDayClicked(DayWeather dayWeatherClicked) {
            if (mInteractionListener != null) {
                mInteractionListener.onDayWeatherClicked(dayWeatherClicked);
            }
        }
    };
    private SwipeRefreshLayout.OnRefreshListener onSwipeToRefresh = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            shouldUpdate = true;
            requestWeatherData();
        }
    };

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
        Activity activity = getActivity();

        if (activity instanceof FragmentInteraction) {
            mInteractionListener = (FragmentInteraction) activity;
        }

        if (savedInstanceState != null) {
            mLastWeatherResponse = Parcels.unwrap(savedInstanceState.getParcelable(DATA_KEY));
            mDayWeatherList = new ArrayList<>(mLastWeatherResponse.getList());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle data) {
        data.putParcelable(DATA_KEY, Parcels.wrap(mLastWeatherResponse));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_week_weather_list, container, false);
        ButterKnife.bind(this, rootView);

        swipeLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeLayout.setRefreshing(true);
        swipeLayout.setOnRefreshListener(onSwipeToRefresh);

        recyclerViewWeekWeather.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewWeekWeather.setHasFixedSize(true);

        shouldUpdate = mDayWeatherList == null;

        if (mDayWeatherList == null) {
            mDayWeatherList = new ArrayList<>();
        } else if (mLastWeatherResponse != null){
            setWeatherResponse(mLastWeatherResponse);
        }

        mAdapter = new WeatherAdapter(mDayWeatherList, mDayClickListener);
        recyclerViewWeekWeather.setAdapter(mAdapter);

        api = WeatherService.getInstance().getAPI();

        requestWeatherData();
        return rootView;
    }

    private void requestWeatherData() {
        if (shouldUpdate) {
            api.getWeatherFor("Buenos Aires", "json", "metric", WeatherService.getApiKey())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<WeatherResponse>() {
                        @Override
                        public void onCompleted() {
                            mAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {
                            new AlertDialog.Builder(getActivity()).setTitle(getActivity().getString(R.string.network_error_title))
                                    .setMessage(getActivity().getString(R.string.network_error_description))
                                    .setPositiveButton(getActivity().getString(R.string.action_accept), null)
                                    .show();
                            swipeLayout.setRefreshing(false);
                        }

                        @Override
                        public void onNext(WeatherResponse weatherResponse) {
                            setWeatherResponse(weatherResponse);
                        }
                    });
            shouldUpdate = false;
        }
    }

    private void setWeatherResponse(WeatherResponse weatherResponse) {
        mLastWeatherResponse = weatherResponse;
        imageCountryFlag.setImageURI(Uri.parse(getActivity().getString(R.string.flags_host, weatherResponse.getCity().getCountry().toLowerCase())));
        textCityName.setText(getString(R.string.country_name_with_country_code, weatherResponse.getCity().getName(), weatherResponse.getCity().getCountry()));
        textLatAndLon.setText(getString(R.string.lat_and_lon, weatherResponse.getCity().getCoord().getLat(), weatherResponse.getCity().getCoord().getLon()));
        mDayWeatherList.clear();
        mDayWeatherList.addAll(weatherResponse.getList());
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteraction) {
            mInteractionListener = (FragmentInteraction) context;
        }
    }

    @Override
    public void onDetach(){
        mInteractionListener = null;
        super.onDetach();
    }
}
