package com.example.zolotuhinartem.simpleweather.weather;

import com.example.zolotuhinartem.simpleweather.objects.City;
import com.example.zolotuhinartem.simpleweather.weather.pojo.Weather;

import java.util.Set;

/**
 * Created by zolotuhinartem on 31.10.16.
 */

public class WeatherResponse {
    private Weather weather;
    private int code;
    private City city;

    public WeatherResponse(Weather weather, City city, int code) {
        this.weather = weather;
        this.code = code;
        this.city = city;

    }

    public City getCity() {
        return city;
    }

    public Weather getWeather() {
        return weather;
    }

    public int getCode() {
        return code;
    }
}
