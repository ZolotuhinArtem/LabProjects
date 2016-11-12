package com.example.zolotuhinartem.simpleweather.hoderfragments;

import android.app.Fragment;
import android.os.Bundle;

import com.example.zolotuhinartem.simpleweather.objects.City;

import java.util.Set;

/**
 * Created by zolotuhinartem on 12.11.16.
 */

public class CitySetHolderFragment extends Fragment{

    private Set<City> citySet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public Set<City> getCitySet() {
        return citySet;
    }

    public void setCitySet(Set<City> citySet) {
        this.citySet = citySet;
    }
}
