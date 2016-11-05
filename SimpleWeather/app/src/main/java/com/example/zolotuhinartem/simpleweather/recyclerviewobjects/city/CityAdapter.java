package com.example.zolotuhinartem.simpleweather.recyclerviewobjects.city;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.zolotuhinartem.simpleweather.R;
import com.example.zolotuhinartem.simpleweather.objects.City;

import java.util.List;

/**
 * Created by zolotuhinartem on 29.10.16.
 */

public class CityAdapter extends RecyclerView.Adapter<CityViewHolder> {

    private List<City> list;
    private CityOnClickListener listener;
    private Activity activity;


    public CityAdapter(Activity activity){
        this.activity = activity;
    }

    public CityAdapter(Activity activity, List<City> list){
        this(activity);
        this.setList(list);
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch(viewType){
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
                return new CityViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        if (this.list != null) {
            City city = list.get(position);
            if (city != null) {
                if (holder instanceof CityViewHolder) {
                    holder.bind(city, this.listener);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }

    }



    public void setList(List<City> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void removeCity(City city) {
        if (city != null) {
            int position = list.indexOf(city);
            if (position >= 0) {
                this.list.remove(position);
                notifyItemRemoved(position);
            }
            /*
            this.list.remove(city);
            notifyDataSetChanged();
            */


        }


    }

    public CityOnClickListener getListener() {
        return listener;
    }

    public void setListener(CityOnClickListener listener) {
        this.listener = listener;
    }
}
