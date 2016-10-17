package com.example.zolotuhinartem.contactlist;

import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zolotuhinartem on 17.10.16.
 */

public class JSONDataNumberList implements DataNumberList {
    private String key;
    private SharedPreferences sharedPreferences;

    public JSONDataNumberList(SharedPreferences sharedPreferences, String key) {
        this.key = key;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public List<Contact> getAll() {
        if ((this.sharedPreferences != null) && (this.key != null)) {

            try {

                JSONArray jsonArray = new JSONArray(this.sharedPreferences.getString(this.key, "[]"));
                ArrayList<Contact> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    list.add(new Contact(jsonArray.getString(i)));
                }
                return list;
            } catch (JSONException e) {
                e.printStackTrace();
                return new ArrayList<Contact>();
            }

        } else {
            return new ArrayList<Contact>();
        }
    }

    @Override
    public void addList(List<Contact> list) {
        if (list != null) {
            if (list.size() != 0) {
                List<Contact> oldList = this.getAll();
                if (oldList != null) {
                    oldList.addAll(list);

                    String data;
                    JSONArray jsonArray = new JSONArray();
                    for (Contact i : oldList) {
                        if (i != null) {
                            jsonArray.put(i.getNumber());
                        }
                    }
                    data = jsonArray.toString();

                    if (this.sharedPreferences != null) {
                        SharedPreferences.Editor editor = this.sharedPreferences.edit();
                        editor.putString(this.key, data);
                        editor.commit();
                    }
                }
            }
        }
    }

    @Override
    public void add(Contact contact) {
        if (contact != null) {
            List<Contact> oldList = this.getAll();
            if (oldList != null) {
                oldList.add(contact);

                String data;
                JSONArray jsonArray = new JSONArray();
                for (Contact i : oldList) {
                    if (i != null) {
                        jsonArray.put(i.getNumber());
                    }
                }
                data = jsonArray.toString();

                if (this.sharedPreferences != null) {
                    SharedPreferences.Editor editor = this.sharedPreferences.edit();
                    editor.putString(this.key, data);
                    editor.commit();
                }
            }
        }
    }

    @Override
    public void clear() {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString(this.key, "[]");
        editor.commit();
    }

    @Override
    public void remove(Contact contact) {
        List<Contact> list = this.getAll();
        list.remove(contact);
        this.clear();
        this.addList(list);
    }
}
