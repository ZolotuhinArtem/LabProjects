package com.example.zolotuhinartem.simpleweather.repositories;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.ArraySet;

import com.example.zolotuhinartem.simpleweather.objects.City;
import com.example.zolotuhinartem.simpleweather.objects.utils.CityManager;
import com.example.zolotuhinartem.simpleweather.utils.StringBuilderUtils;
import com.example.zolotuhinartem.simpleweather.utils.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zolotuhinartem on 02.11.16.
 */

public class CityRepository {

    private static CityRepository cityRepository = null;

    private Set<City> set;

    public static CityRepository getCityRepository(Context context) {
        if (cityRepository != null) {
            return cityRepository;
        } else {
            cityRepository = new CityRepository(context);
            return cityRepository;
        }
    }

    private CityRepository(Context context) {
        this.set = new HashSet<>();
        AssetManager assetManager = null;
        BufferedReader reader = null;

        try {
            assetManager = context.getAssets();
            reader = new BufferedReader(new InputStreamReader(assetManager.open("cities")));

            String line = "";

            City city;

            while ((line = reader.readLine()) != null) {
                city = CityManager.parse(line);
                if (city != null) {
                    set.add(city);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            try {
//                assetManager.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }

    public Set<City> findByName(String name) {
        Set<City> result = new HashSet<>();

        name = StringUtils.deleteSpaces(name).toLowerCase();

        String tempName;

        for (City i: this.set) {
            tempName = StringUtils.deleteSpaces(i.getName().toLowerCase());
            if (tempName.equals(name)) {
                result.add(i);
            }
        }

        return result;
    }


}
