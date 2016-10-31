package com.example.zolotuhinartem.simpleweather.weather;

import com.example.zolotuhinartem.simpleweather.objects.City;
import com.example.zolotuhinartem.simpleweather.weather.pojo.Weather;

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

    public void setCity(City city) {
        this.city = city;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
