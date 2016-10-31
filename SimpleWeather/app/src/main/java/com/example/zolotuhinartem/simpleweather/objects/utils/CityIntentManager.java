package com.example.zolotuhinartem.simpleweather.objects.utils;

import android.content.Intent;

import com.example.zolotuhinartem.simpleweather.objects.City;

/**
 * Created by zolotuhinartem on 29.10.16.
 */

public class CityIntentManager {
    public static Intent fill(Intent intent, City city) {
        intent.putExtra(City.ATTRIBUTE_CITY, city.getCity());
        intent.putExtra(City.ATTRIBUTE_COUNTRY, city.getCountry());
        return intent;
    }

    public static City get(Intent intent) {
        return new City(intent.getStringExtra(City.ATTRIBUTE_CITY),
                        intent.getStringExtra(City.ATTRIBUTE_COUNTRY));
    }
}
