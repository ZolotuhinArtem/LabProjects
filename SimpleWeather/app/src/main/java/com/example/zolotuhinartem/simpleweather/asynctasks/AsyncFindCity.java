package com.example.zolotuhinartem.simpleweather.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import com.example.zolotuhinartem.simpleweather.objects.City;
import com.example.zolotuhinartem.simpleweather.repositories.CityRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zolotuhinartem on 02.11.16.
 */

public class AsyncFindCity extends AsyncTask<String, Void, Set<City>> {

    private AsyncFindCity.OnCompleteListener listener;
    private Context context;

    public AsyncFindCity(Context context) {
        this.context = context;
    }

    public void setListener(OnCompleteListener listener) {
        this.listener = listener;
    }

    @Override
    protected Set<City> doInBackground(String... strings) {
        if (strings != null) {
            if (strings.length > 0) {
                if (strings[0] != null){
                    CityRepository cityRepository = CityRepository.getCityRepository(context);
                    Set<City> cities = cityRepository.findByName(strings[0]);
                    return cities;
                }
            }
        }
        return new HashSet<>();
    }

    @Override
    protected void onPostExecute(Set<City> cities) {
        if (listener != null) {
            listener.onComplete(cities);
        }
    }

    public interface OnCompleteListener {
        void onComplete(Set<City> cities);
    }
}
