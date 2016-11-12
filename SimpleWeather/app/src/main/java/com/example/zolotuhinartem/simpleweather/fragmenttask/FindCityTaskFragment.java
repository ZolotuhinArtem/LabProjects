package com.example.zolotuhinartem.simpleweather.fragmenttask;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.zolotuhinartem.simpleweather.fragmenttask.callback.FindCityCallback;
import com.example.zolotuhinartem.simpleweather.hoderfragments.CitySetHolderFragment;
import com.example.zolotuhinartem.simpleweather.objects.City;
import com.example.zolotuhinartem.simpleweather.repositories.CityRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zolotuhinartem on 12.11.16.
 */

public class FindCityTaskFragment extends Fragment {

    private FindCityCallback findCityCallback;
    private Context context;
    private FindCityAsyncTask asyncTask;


    public void execute(String text) {
        if (asyncTask == null) {
            asyncTask = new FindCityAsyncTask(this.context);
            asyncTask.execute(text);
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
        this.findCityCallback = null;
    }

    private void attach(Context context) {
        this.context = context;
        try {
            this.findCityCallback = (FindCityCallback) this.context;
        } catch (ClassCastException ex) {
            //ex.printStackTrace();
        }
    }

    public boolean isWorking() {
        return asyncTask != null;
    }

    public class FindCityAsyncTask extends AsyncTask<String, Void, Set<City>> {

        private Context context;


        public FindCityAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Set<City> doInBackground(String... strings) {
            if (strings != null) {
                if (strings.length > 0) {
                    if (strings[0] != null) {
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        CityRepository cityRepository = CityRepository.getCityRepository(context);
                        Set<City> cities = cityRepository.findByName(strings[0]);
                        if (cities != null) {
                            return cities;
                        }
                    }
                }
            }
            return new HashSet<>();
        }

        @Override
        protected void onPostExecute(Set<City> cities) {
            if (findCityCallback != null) {
                findCityCallback.resultFindCity(cities);
            }
            asyncTask = null;
        }

    }

}
