package com.contreras.cristian.weatherappcristiancontreras.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.contreras.cristian.weatherappcristiancontreras.R;
import com.contreras.cristian.weatherappcristiancontreras.model.DayWeather;
import com.contreras.cristian.weatherappcristiancontreras.model.WeatherData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.contreras.cristian.weatherappcristiancontreras.Utils.getFormattedDateFromTimeInSeconds;
import static com.contreras.cristian.weatherappcristiancontreras.Utils.getResourceFromCode;
import static com.contreras.cristian.weatherappcristiancontreras.Utils.getStringTempFromFloat;

/**
 * Created by Pato on 10/8/2015.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private final List<DayWeather> mItems;
    private final DayClickListener mDayClickListener;

    public WeatherAdapter(List<DayWeather> items, DayClickListener dayClickListener) {
        mItems = items;
        mDayClickListener = dayClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(ViewHolder.getLayout(), parent, false);
        return new ViewHolder(v, mDayClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.layoutItem) RelativeLayout layoutItem;
        @Bind(R.id.imageWeather) ImageView imageWeather;
        @Bind(R.id.textDate) TextView textDate;
        @Bind(R.id.textTempMax) TextView textTempMax;
        @Bind(R.id.textTempMin) TextView textTempMin;

        private final DayClickListener mDayClickListener;

        public ViewHolder(View view, DayClickListener dayClickListener) {
            super(view);

            mDayClickListener = dayClickListener;
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.layoutItem)
        public void onItemClicked(View view) {
            if (mDayClickListener != null) {
                mDayClickListener.onDayClicked((DayWeather) view.getTag());
            }
        }

        public static int getLayout() {
            return R.layout.view_item_weather_resumed;
        }

        public void bind(DayWeather item) {
            textTempMax.setText(getStringTempFromFloat(item.getTemp().getMax()));
            textTempMin.setText(getStringTempFromFloat(item.getTemp().getMin()));

            textDate.setText(getFormattedDateFromTimeInSeconds(item.getDt()));

            layoutItem.setTag(item);

            if (item.getWeather() != null && item.getWeather().size() > 0) {
                WeatherData weatherData = item.getWeather().get(0);

                imageWeather.setImageResource(getResourceFromCode(weatherData.getIcon()));
            }
        }
    }

    public interface DayClickListener {
        void onDayClicked(DayWeather dayWeatherClicked);
    }
}
