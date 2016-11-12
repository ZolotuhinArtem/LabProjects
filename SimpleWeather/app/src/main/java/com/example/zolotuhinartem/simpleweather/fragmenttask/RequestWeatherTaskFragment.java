package com.example.zolotuhinartem.simpleweather.fragmenttask;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.zolotuhinartem.simpleweather.fragmenttask.callback.RequestWeatherCallback;
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
 * Created by zolotuhinartem on 12.11.16.
 */

public class RequestWeatherTaskFragment extends Fragment {


    private RequestWeatherCallback requestWeatherCallback;
    private RequestWeatherAsyncTask requestWeatherAsyncTask;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void execute(City city) {
        if (requestWeatherAsyncTask == null) {
            requestWeatherAsyncTask = new RequestWeatherAsyncTask();
            requestWeatherAsyncTask.execute(city);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.requestWeatherCallback = null;
    }

    private void attach(Context context){
        try {
            this.requestWeatherCallback = (RequestWeatherCallback) context;
        } catch (ClassCastException ex){

        }
    }

    public boolean isWorking(){
        return requestWeatherAsyncTask != null;
    }


    public class RequestWeatherAsyncTask extends AsyncTask<City, Void, WeatherResponse> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected WeatherResponse doInBackground(City... params) {
            int code = 404;
            if (params != null) {
                if (params[0] != null) {
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
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
            requestWeatherAsyncTask = null;
            if (requestWeatherCallback != null) {
                requestWeatherCallback.resultRequestWeather(weatherResponse);
            }
        }

    }
}
