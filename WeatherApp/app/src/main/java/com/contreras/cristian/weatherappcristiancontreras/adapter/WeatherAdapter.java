package com.contreras.cristian.weatherappcristiancontreras.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.contreras.cristian.weatherappcristiancontreras.R;
import com.contreras.cristian.weatherappcristiancontreras.model.WeatherData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Pato on 10/8/2015.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private Context mContext;
    private List<WeatherData> mItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageWeather) ImageView imageWeather;
        @Bind(R.id.textDate) TextView textDate;
        @Bind(R.id.textTempMax) TextView textTempMax;
        @Bind(R.id.textTempMin) TextView textTempMin;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
        public static int getLayout() {
            return R.layout.view_item_weather_resumed;
        }

        public void bind(WeatherData item) {
            //textTemp.setText(String.valueOf(item.temp.getMin()) + " - "+ String.valueOf(item.temp.getMax()));
            textTempMax.setText(String.format("%dº", Math.round(item.temp.getMax())));
            textTempMin.setText(String.format("%dº", Math.round(item.temp.getMin())));
        }
    }

    public WeatherAdapter(List<WeatherData> items) {
        mItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(ViewHolder.getLayout(), parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherData item = mItems.get(position);

        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
