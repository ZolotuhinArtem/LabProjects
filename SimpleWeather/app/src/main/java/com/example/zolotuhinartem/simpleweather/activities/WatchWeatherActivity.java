package com.example.zolotuhinartem.simpleweather.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.zolotuhinartem.simpleweather.R;
import com.example.zolotuhinartem.simpleweather.asynctasks.AsyncRequestWeather;
import com.example.zolotuhinartem.simpleweather.objects.City;
import com.example.zolotuhinartem.simpleweather.objects.utils.CityManager;
import com.example.zolotuhinartem.simpleweather.weather.WeatherResponse;
import com.example.zolotuhinartem.simpleweather.weather.pojo.Weather;

public class WatchWeatherActivity extends AppCompatActivity implements AsyncRequestWeather.OnCompleteListener{

    private ProgressBar progressBar;

    private TextView tvCity, tvTemp;

    private View vInfo;

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
        if (city != null) {
            setProgress(true);
            AsyncRequestWeather asyncRequestWeather = new AsyncRequestWeather(this);
            asyncRequestWeather.execute(city);
        } else {
            finish();
        }


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
    public void onComplete(WeatherResponse weather) {
        setProgress(false);
        int code = weather.getCode();
        if (code < 400) {
            tvCity.setText(weather.getCity().getName());
            int temp = (int)Math.round(weather.getWeather().getMain().getTemp() - 273.15) ;
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
