package com.example.zolotuhinartem.simpleweather.recyclerviewobjects.foundedcity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zolotuhinartem.simpleweather.R;
import com.example.zolotuhinartem.simpleweather.objects.City;

/**
 * Created by zolotuhinartem on 02.11.16.
 */

public class FoundedCityViewHolder extends RecyclerView.ViewHolder {

    private TextView tvName;
    private Button btnAdd;

    public FoundedCityViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_founded_city_item_city_name);
        btnAdd = (Button) itemView.findViewById(R.id.btn_founded_city_item_add);
    }

    public void bind(final City city, final FoundedCityAdapter.OnAddClickListener listener) {
        if (tvName != null) {
            tvName.setText(city.getCountryCode() + ": " + city.getName());
        }

        if (btnAdd != null) {
            if (listener != null) {
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onAddClick(city);
                    }
                });
            }
        }

    }
}
