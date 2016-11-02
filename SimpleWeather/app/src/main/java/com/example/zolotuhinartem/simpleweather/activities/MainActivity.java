package com.example.zolotuhinartem.simpleweather.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.zolotuhinartem.simpleweather.R;
import com.example.zolotuhinartem.simpleweather.recyclerviewobjects.city.CityAdapter;
import com.example.zolotuhinartem.simpleweather.recyclerviewobjects.city.CityOnClickListener;
import com.example.zolotuhinartem.simpleweather.objects.City;
import com.example.zolotuhinartem.simpleweather.objects.utils.CityManager;
import com.example.zolotuhinartem.simpleweather.repositories.UserCityRepository;
import com.example.zolotuhinartem.simpleweather.utils.SetToListConvertor;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, CityOnClickListener{

    public static final int REQUEST_ADD_CITY_ACTIVITY = 0;
    public static final String SHARED_PREFERENCES_CITY = "shared_preferences_city";


    private CityAdapter adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_CITY, MODE_PRIVATE);
        List<City> list = SetToListConvertor.setToList(UserCityRepository.getAll(sharedPreferences));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_main_activity);
        adapter = new CityAdapter(this);
        adapter.setList(list);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btnAdd = (Button) findViewById(R.id.btn_main_activity_add);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_main_activity_add:
                addClick();
                break;
        }
    }

    private void addClick() {
        Intent intent = new Intent(this, AddCityActivity.class);
        startActivityForResult(intent, REQUEST_ADD_CITY_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_ADD_CITY_ACTIVITY:
                if (data != null) {
                    City city = CityManager.getFromIntent(data);
                    if (city != null) {
                        if ((this.adapter != null) && (sharedPreferences != null)) {
                            sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_CITY, MODE_PRIVATE);
                            UserCityRepository.add(sharedPreferences, city);

                            List<City> list = SetToListConvertor.setToList(UserCityRepository.getAll(sharedPreferences));
                            adapter.setList(list);
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onClick(City city) {
        if (city != null) {
            Intent intent = new Intent(this, WatchWeatherActivity.class);
            intent = CityManager.fillIntent(intent, city);
            startActivity(intent);
        }
    }

    @Override
    public void onDeleteClick(City city) {
        if (city != null) {
            UserCityRepository.remove(sharedPreferences, city);
            adapter.removeCity(city);
        }
    }
}
