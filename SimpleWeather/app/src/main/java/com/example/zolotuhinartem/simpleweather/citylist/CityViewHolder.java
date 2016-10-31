package com.example.zolotuhinartem.simpleweather.citylist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.zolotuhinartem.simpleweather.R;
import com.example.zolotuhinartem.simpleweather.objects.City;

/**
 * Created by zolotuhinartem on 29.10.16.
 */

public class CityViewHolder extends RecyclerView.ViewHolder {

    private TextView tvCityName;

    public CityViewHolder(View itemView) {
        super(itemView);
        tvCityName = (TextView) itemView.findViewById(R.id.tv_city_item_city_name);
    }
    public void bind(final City city, final CityOnClickListener listener){
        if (tvCityName != null) {
            if (city != null) {
                tvCityName.setText(city.getCity());
            }
        }
        if (listener != null){

        }
    }
}
