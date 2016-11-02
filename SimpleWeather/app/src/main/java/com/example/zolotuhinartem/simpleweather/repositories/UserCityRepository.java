package com.example.zolotuhinartem.simpleweather.repositories;

import android.content.SharedPreferences;

import com.example.zolotuhinartem.simpleweather.objects.City;
import com.example.zolotuhinartem.simpleweather.objects.utils.CityManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zolotuhinartem on 29.10.16.
 */

public class UserCityRepository {

    public static String KEY_CITY = "City";

    public static Set<City> getAll(SharedPreferences sharedPreferences) {
        Set<City> list = null;
        try {
            JSONArray jarray = new JSONArray(sharedPreferences.getString(KEY_CITY, "[]"));
            list = new HashSet<>(jarray.length());
            for (int i = 0; i < jarray.length(); i++) {
                City city = CityManager.getFromJson((JSONObject) jarray.get(i));
                if (city != null) {
                    list.add(city);
                }
            }
            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean add(SharedPreferences sharedPreferences, City city) {
        boolean result = false;

        if (city != null) {
            Set<City> list = getAll(sharedPreferences);

            if (list == null) {
                list = new HashSet<>(1);
            }
            list.add(city);

            JSONArray jarray = new JSONArray();
            jarray = fillJsonArray(jarray, list);


            return saveJSONArray(sharedPreferences, jarray);
        }
        return result;
    }

    private static boolean saveJSONArray(SharedPreferences sharedPreferences, JSONArray jarray) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_CITY, jarray.toString());
        editor.commit();
        return true;
    }

    public static boolean add(SharedPreferences sharedPreferences, Set<City> list) {
        Set<City> oldList = getAll(sharedPreferences);
        if (oldList == null) {
            oldList = list;
        } else {
            oldList.addAll(list);
        }

        JSONArray jarray = new JSONArray();
        jarray = fillJsonArray(jarray, list);

        return saveJSONArray(sharedPreferences, jarray);
    }

    public static boolean clear(SharedPreferences sharedPreferences) {
        return saveJSONArray(sharedPreferences, new JSONArray());
    }


    public static boolean remove(SharedPreferences sharedPreferences, City city) {
        boolean result = false;
        Set<City> list = getAll(sharedPreferences);
        result = list.remove(city);
        if (result) {
            clear(sharedPreferences);
            result = add(sharedPreferences, list);
        }
        return result;
    }

    private static JSONArray fillJsonArray(JSONArray jarray, Set<City> list) {
        if (jarray == null) {
            jarray = new JSONArray();
        }
        if (list != null){
            for (City i : list) {
                if (i != null) {
                    JSONObject jobject = new JSONObject();
                    jobject = CityManager.fillJson(jobject, i);
                    if (jobject != null) {
                        jarray.put(jobject);
                    }
                }
            }
        }

        return jarray;
    }
}
