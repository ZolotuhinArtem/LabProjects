package com.example.zolotuhinartem.simpleweather.objects.utils;

import com.example.zolotuhinartem.simpleweather.objects.City;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zolotuhinartem on 29.10.16.
 */

public class CityJsonManager {
    public static JSONObject fill(JSONObject jobject, City city) {
        try {
            jobject.put(City.ATTRIBUTE_CITY, city.getCity());
            jobject.put(City.ATTRIBUTE_COUNTRY, city.getCountry());
            return jobject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static City get(JSONObject jobject) {
        try {
            String stringCity = jobject.getString(City.ATTRIBUTE_CITY);
            String stringCountry = jobject.getString(City.ATTRIBUTE_COUNTRY);
            if (stringCity != null) {
                if (!stringCity.equals("")){
                    if (stringCountry != null) {
                        if (!stringCountry.equals("")) {
                            City city = new City(stringCity, stringCountry);
                            return city;
                        }
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
