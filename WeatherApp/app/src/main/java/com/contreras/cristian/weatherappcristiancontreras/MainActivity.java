package com.contreras.cristian.weatherappcristiancontreras;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.contreras.cristian.weatherappcristiancontreras.fragments.AboutFragment;
import com.contreras.cristian.weatherappcristiancontreras.fragments.FragmentInteraction;
import com.contreras.cristian.weatherappcristiancontreras.fragments.WeatherDetailFragment;
import com.contreras.cristian.weatherappcristiancontreras.fragments.WeekWeatherListFragment;
import com.contreras.cristian.weatherappcristiancontreras.model.DayWeather;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FragmentInteraction {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setTitle(getString(R.string.weather_app_title));

        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.fragmentContainer) == null) {
            fm.beginTransaction().add(R.id.fragmentContainer, WeekWeatherListFragment.newInstance()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_about:
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
                if (!(fragment instanceof AboutFragment)) {
                    loadFragment(AboutFragment.newInstance());
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDayWeatherClicked(DayWeather dayWeather) {
        loadFragment(WeatherDetailFragment.newInstance(dayWeather));
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter, R.anim.leave, R.anim.pop_enter, R.anim.pop_exit);
        ft.replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit();
    }
}
