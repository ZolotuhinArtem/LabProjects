package com.example.zolotuhinartem.simpleweather.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.zolotuhinartem.simpleweather.R;
import com.example.zolotuhinartem.simpleweather.fragmenttask.RequestWeatherTaskFragment;
import com.example.zolotuhinartem.simpleweather.fragmenttask.callback.RequestWeatherCallback;
import com.example.zolotuhinartem.simpleweather.objects.City;
import com.example.zolotuhinartem.simpleweather.objects.utils.CityManager;
import com.example.zolotuhinartem.simpleweather.weather.WeatherResponse;

public class WatchWeatherActivity extends AppCompatActivity implements RequestWeatherCallback {


    public static final String IS_CALLED = "is_called";
    public static final String CITY_NAME = "city_name";
    public static final String CITY_TEMPERATURE = "city_temperature";


    private ProgressBar progressBar;

    private TextView tvCity, tvTemp;

    private View vInfo;
    private RequestWeatherTaskFragment requestWeatherTaskFragment;

    private boolean isCalled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_weather);

        vInfo = findViewById(R.id.activity_watch_weather_info);

        tvCity = (TextView) findViewById(R.id.tv_activity_watch_weather_city);
        tvTemp = (TextView) findViewById(R.id.tv_activity_watch_weather_temp);

        progressBar = (ProgressBar) findViewById(R.id.pb_activity_watch_weather);

        Intent intent = getIntent();
        City city = CityManager.getFromIntent(intent);

        if (savedInstanceState != null) {
            isCalled = true;
            loadInstance(savedInstanceState);
        } else {
            isCalled = false;
        }

        FragmentManager fragmentManager = getFragmentManager();

        requestWeatherTaskFragment = (RequestWeatherTaskFragment) fragmentManager.findFragmentByTag(RequestWeatherTaskFragment.class.getName());

        if (requestWeatherTaskFragment == null) {
            requestWeatherTaskFragment = new RequestWeatherTaskFragment();
            fragmentManager.beginTransaction().add(requestWeatherTaskFragment, RequestWeatherTaskFragment.class.getName()).commit();
        }

        if (!isCalled) {
            requestWeatherTaskFragment.execute(city);
            isCalled = true;
        }

        setProgress(requestWeatherTaskFragment.isWorking());


    }

    private void loadInstance(Bundle savedInstanceState) {
        tvCity.setText(savedInstanceState.getString(CITY_NAME, "%ERROR%"));
        tvTemp.setText(savedInstanceState.getString(CITY_TEMPERATURE, "%ERROR%"));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CITY_NAME, tvCity.getText().toString());
        outState.putString(CITY_TEMPERATURE, tvTemp.getText().toString());
    }

    private void setProgress(boolean progress) {
        if (progress) {
            vInfo.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            vInfo.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public void resultRequestWeather(WeatherResponse weather) {
        setProgress(false);
        int code = weather.getCode();
        if (code < 400) {
            tvCity.setText(weather.getCity().getName());
            int temp = (int) Math.round(weather.getWeather().getMain().getTemp() - 273.15);
            String sign = "";
            if (temp > 0) {
                sign = "+";
            }
            tvTemp.setText(sign + temp + " °C");
        } else {

            tvCity.setText("Error! code: " + weather.getCode());
            tvTemp.setText("");
        }
    }
}
