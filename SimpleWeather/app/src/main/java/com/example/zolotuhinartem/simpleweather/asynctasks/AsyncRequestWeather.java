package com.example.zolotuhinartem.simpleweather.asynctasks;

import android.os.AsyncTask;

import com.example.zolotuhinartem.simpleweather.objects.City;
import com.example.zolotuhinartem.simpleweather.weather.OpenWeatherApi;
import com.example.zolotuhinartem.simpleweather.weather.WeatherResponse;
import com.example.zolotuhinartem.simpleweather.weather.pojo.Weather;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zolotuhinartem on 31.10.16.
 */

public class AsyncRequestWeather extends AsyncTask<City, Void, WeatherResponse>{

    private OnCompleteListener listener;


    public AsyncRequestWeather(OnCompleteListener listener) {
        this.listener = listener;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected WeatherResponse doInBackground(City... params) {
        int code = 404;
        if (params != null) {
            if (params[0] != null) {
                City city = params[0];
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(OpenWeatherApi.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                OpenWeatherApi weatherApi = retrofit.create(OpenWeatherApi.class);
                Call weatherCall = weatherApi.getWeather(city.getId(), OpenWeatherApi.API_ID);
                try {
                    Response response = weatherCall.execute();
                    code = response.code();
                    WeatherResponse weatherResponse = new WeatherResponse((Weather) response.body(), city, code);
                    return weatherResponse;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }
        return new WeatherResponse(null, null, code);
    }

    @Override
    protected void onPostExecute(WeatherResponse weatherResponse) {
        listener.onComplete(weatherResponse);
    }

    public interface OnCompleteListener {
        void onComplete(WeatherResponse weather);
    }
}
