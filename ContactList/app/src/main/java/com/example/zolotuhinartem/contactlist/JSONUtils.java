package com.example.zolotuhinartem.contactlist;

import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zolotuhinartem on 15.10.16.
 */

public class JSONUtils {

    public static List<Contact> getContactListFromSharedPreferences(SharedPreferences sharedPreferences, String key) {

        if ((sharedPreferences != null) && (key != null)) {

            try {
                JSONArray jsonArray = new JSONArray(sharedPreferences.getString(key, ""));
                ArrayList<Contact> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    list.add(new Contact(jsonArray.getString(i)));
                }
                return list;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

        } else {
            return null;
        }

    }

    public static boolean saveContactListIntoSharedPreferences(List<Contact> list, SharedPreferences sharedPreferences, String key) {
        boolean result = false;

        String data;
        JSONArray jsonArray = new JSONArray();
        for (Contact i : list) {
            if (i != null) {
                jsonArray.put(i.getNumber());
            }
        }
        data = jsonArray.toString();

        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, data);
            editor.commit();
            result = true;
        }

        return result;

    }

}
