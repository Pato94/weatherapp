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
public class AboutFragment extends Fragment {

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();

        fragment.setArguments(args);

        return fragment;
    }

    public AboutFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }
}
