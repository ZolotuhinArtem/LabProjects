package com.example.zolotuhinartem.simpleweather.objects.utils;

import android.content.Intent;

import com.example.zolotuhinartem.simpleweather.objects.City;
import com.example.zolotuhinartem.simpleweather.utils.StringBuilderUtils;
import com.example.zolotuhinartem.simpleweather.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zolotuhinartem on 02.11.16.
 */

public class CityManager {
    // WORK WITH INTENT
    public static Intent fillIntent(Intent intent, City city) {
        intent.putExtra(City.ATTRIBUTE_CITY_ID, city.getId());
        intent.putExtra(City.ATTRIBUTE_CITY_NAME, city.getName());
        intent.putExtra(City.ATTRIBUTE_CITY_LAT, city.getLat());
        intent.putExtra(City.ATTRIBUTE_CITY_LON, city.getLon());
        intent.putExtra(City.ATTRIBUTE_CITY_COUNTRY_CODE, city.getCountryCode());
        return intent;
    }

    public static City getFromIntent(Intent intent) {
        return new City(intent.getStringExtra(City.ATTRIBUTE_CITY_ID),
                intent.getStringExtra(City.ATTRIBUTE_CITY_NAME),
                intent.getStringExtra(City.ATTRIBUTE_CITY_LAT),
                intent.getStringExtra(City.ATTRIBUTE_CITY_LON),
                intent.getStringExtra(City.ATTRIBUTE_CITY_COUNTRY_CODE)
        );
    }

    // WORK WITH JSON


    public static JSONObject fillJson(JSONObject jobject, City city) {
        try {
            jobject.put(City.ATTRIBUTE_CITY_ID, city.getId());
            jobject.put(City.ATTRIBUTE_CITY_NAME, city.getName());
            jobject.put(City.ATTRIBUTE_CITY_LAT, city.getLat());
            jobject.put(City.ATTRIBUTE_CITY_LON, city.getLon());
            jobject.put(City.ATTRIBUTE_CITY_COUNTRY_CODE, city.getCountryCode());
            return jobject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static City getFromJson(JSONObject jobject) {
        try {
            String id = jobject.getString(City.ATTRIBUTE_CITY_ID);
            String name = jobject.getString(City.ATTRIBUTE_CITY_NAME);
            String lat = jobject.getString(City.ATTRIBUTE_CITY_LAT);
            String lon = jobject.getString(City.ATTRIBUTE_CITY_LON);
            String countryCode = jobject.getString(City.ATTRIBUTE_CITY_COUNTRY_CODE);
            if ((id != null) && (name != null) && (lat != null) && (lon != null) && (countryCode != null)) {

                return  new City(id, name, lat, lon, countryCode);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static City parse(String line) {
        line = StringUtils.deleteSpacesOnStartAndEnd(line);
        StringBuilder str = new StringBuilder(line);

        char tempChar;
        char tempChar2;

        StringBuilder id = new StringBuilder();
        StringBuilder name = new StringBuilder();
        StringBuilder lat = new StringBuilder();
        StringBuilder lon = new StringBuilder();
        StringBuilder countryCode = new StringBuilder();


        //--------------- ID

        for (int i = 0; i < str.length(); i++) {
            tempChar = str.charAt(i);
            str.deleteCharAt(i);
            i--;
            if (((int) tempChar >= (int) '0') && ((int) tempChar <= (int) '9')) {
                id.append(tempChar);
            } else {
                if ((tempChar == ' ') || (tempChar == '\t')) {
                    break;
                } else {
                    return null;
                }
            }
        }

        str = StringBuilderUtils.deleteSpacesOnStartAndEnd(str);

        //--------------- NAME
        for (int i = 0; i < str.length(); i++) {
            tempChar = str.charAt(i);
            tempChar2 = Character.toLowerCase(tempChar);
            if ((((int) tempChar2 >= (int) 'a') && ((int) tempChar2 <= (int) 'z')) || (tempChar2 == ' ') || (tempChar == '\t')) {
                str.deleteCharAt(i);
                i--;
                name.append(tempChar);
            } else {
                break;
            }
        }

        str = StringBuilderUtils.deleteSpacesOnStartAndEnd(str);

        //--------------- lan
        boolean wasSign = false;
        boolean wasDot = false;

        for (int i = 0; i < str.length(); i++) {
            tempChar = str.charAt(i);
            str.deleteCharAt(i);
            i--;
            switch (tempChar) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    break;
                case '+':
                case '-':
                    if (!wasSign) {
                        if (lat.length() == 0) {
                            wasSign = true;
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                    break;
                case '.':
                    if (!wasDot) {
                        wasDot = true;
                    } else {
                        return null;
                    }
                    break;
                case ' ':
                case '\t':
                    if (lat.length() != 0) {
                        i = str.length();
                        break;
                    } else {
                        return null;
                    }

            }
            lat.append(tempChar);
        }

        str = StringBuilderUtils.deleteSpacesOnStartAndEnd(str);

        //-----------------------lon
        wasSign = false;
        wasDot = false;
        for (int i = 0; i < str.length(); i++) {
            tempChar = str.charAt(i);
            str.deleteCharAt(i);
            i--;
            switch (tempChar) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    break;
                case '+':
                case '-':
                    if (!wasSign) {
                        if (lon.length() == 0) {
                            wasSign = true;
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                    break;
                case '.':
                    if (!wasDot) {
                        wasDot = true;
                    } else {
                        return null;
                    }
                    break;
                case ' ':
                case '\t':
                    if (lon.length() != 0) {
                        i = str.length();
                        break;
                    } else {
                        return null;
                    }

            }
            lon.append(tempChar);
        }

        str = StringBuilderUtils.deleteSpacesOnStartAndEnd(str);

        //--------------Country Code
        for (int i = 0; i < str.length(); i++) {
            tempChar = str.charAt(i);
            str.deleteCharAt(i);
            i--;
            countryCode.append(tempChar);
        }

        id = StringBuilderUtils.deleteSpacesOnStartAndEnd(id);
        name = StringBuilderUtils.deleteSpacesOnStartAndEnd(name);
        lat = StringBuilderUtils.deleteSpacesOnStartAndEnd(lat);
        lon = StringBuilderUtils.deleteSpacesOnStartAndEnd(lon);
        countryCode = StringBuilderUtils.deleteSpacesOnStartAndEnd(countryCode);

        return new City(id.toString(), name.toString(), lat.toString(), lon.toString(), countryCode.toString());

    }
}
