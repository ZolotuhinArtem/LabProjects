package com.example.zolotuhinartem.simpleweather.fragmenttask.callback;

import com.example.zolotuhinartem.simpleweather.weather.WeatherResponse;

/**
 * Created by zolotuhinartem on 12.11.16.
 */

public interface RequestWeatherCallback {

    void resultRequestWeather(WeatherResponse weatherResponse);
}
