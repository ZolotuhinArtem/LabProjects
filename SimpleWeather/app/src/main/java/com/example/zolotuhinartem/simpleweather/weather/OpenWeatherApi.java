package com.example.zolotuhinartem.simpleweather.weather;

import com.example.zolotuhinartem.simpleweather.weather.pojo.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zolotuhinartem on 29.10.16.
 */

public interface OpenWeatherApi {

    //"http://api.openweathermap.org/data/2.5/weather?q=kazan,ru&APPID=b9c967d7d9a3857960e1bcb6fbdb75ca"
    String API_ID = "b9c967d7d9a3857960e1bcb6fbdb75ca";
    String BASE_URL = "http://api.openweathermap.org";

    @GET("data/2.5/weather")
    Call<Weather> getWeather(@Query("q") String id, @Query("APPID") String appId);

}
