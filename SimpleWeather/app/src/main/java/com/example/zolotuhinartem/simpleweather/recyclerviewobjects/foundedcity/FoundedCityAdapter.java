package com.example.zolotuhinartem.simpleweather.recyclerviewobjects.foundedcity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zolotuhinartem.simpleweather.R;
import com.example.zolotuhinartem.simpleweather.objects.City;

import java.util.List;


/**
 * Created by zolotuhinartem on 02.11.16.
 */

public class FoundedCityAdapter extends RecyclerView.Adapter<FoundedCityViewHolder> {

    private List<City> list;
    private Context context;
    private OnAddClickListener listener;

    public FoundedCityAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FoundedCityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.founded_city_item, parent, false);
                return new FoundedCityViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(FoundedCityViewHolder holder, int position) {
        City city = list.get(position);
        if (city != null) {
            holder.bind(city, listener);
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public void setListener(OnAddClickListener listener) {
        this.listener = listener;
    }

    public void setList(List<City> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface OnAddClickListener {
        void onAddClick(City city);
    }
}
