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
import com.example.zolotuhinartem.simpleweather.citylist.CityAdapter;
import com.example.zolotuhinartem.simpleweather.objects.City;
import com.example.zolotuhinartem.simpleweather.objects.utils.CityIntentManager;
import com.example.zolotuhinartem.simpleweather.repositories.UserCityRepository;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int REQUEST_ADD_CITY_ACTIVITY = 0;
    public static final String SHARED_PREFERENCES_CITY = "shared_preferences_city";


    private CityAdapter adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_CITY, MODE_PRIVATE);
        List<City> list = UserCityRepository.getAll(sharedPreferences);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_main_activity);
        adapter = new CityAdapter(this);
        adapter.setList(list);
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
                    City city = CityIntentManager.get(data);
                    if (city != null) {
                        if ((this.adapter != null) && (sharedPreferences != null)) {
                            sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_CITY, MODE_PRIVATE);
                            UserCityRepository.add(sharedPreferences, city);
                            adapter.setList(UserCityRepository.getAll(sharedPreferences));
                        }
                    }
                }
                break;
        }
    }
}
