package com.example.zolotuhinartem.simpleweather.recyclerviewobjects.city;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zolotuhinartem.simpleweather.R;
import com.example.zolotuhinartem.simpleweather.objects.City;

/**
 * Created by zolotuhinartem on 29.10.16.
 */

public class CityViewHolder extends RecyclerView.ViewHolder {

    private TextView tvCityName;
    private View clickableWatch;
    private Button btnDelete;

    public CityViewHolder(View itemView) {
        super(itemView);
        tvCityName = (TextView) itemView.findViewById(R.id.tv_city_item_city_name);
        btnDelete = (Button) itemView.findViewById(R.id.btn_city_item_delete);
        clickableWatch = itemView.findViewById(R.id.fl_city_item_click_to_watch);
    }
    public void bind(final City city, final CityOnClickListener listener){
        if (tvCityName != null) {
            if (city != null) {
                tvCityName.setText(city.getName());
            }
        }
        if (listener != null){
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDeleteClick(city);
                }
            });
            clickableWatch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(city);
                }
            });
        }
    }
}
