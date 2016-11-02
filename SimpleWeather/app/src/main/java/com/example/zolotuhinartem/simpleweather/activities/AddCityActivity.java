package com.example.zolotuhinartem.simpleweather.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zolotuhinartem.simpleweather.R;
import com.example.zolotuhinartem.simpleweather.asynctasks.AsyncFindCity;
import com.example.zolotuhinartem.simpleweather.asynctasks.AsyncRequestWeather;
import com.example.zolotuhinartem.simpleweather.objects.City;
import com.example.zolotuhinartem.simpleweather.objects.utils.CityManager;
import com.example.zolotuhinartem.simpleweather.recyclerviewobjects.foundedcity.FoundedCityAdapter;
import com.example.zolotuhinartem.simpleweather.repositories.CityRepository;
import com.example.zolotuhinartem.simpleweather.utils.SetToListConvertor;
import com.example.zolotuhinartem.simpleweather.weather.WeatherResponse;
import com.example.zolotuhinartem.simpleweather.weather.pojo.Weather;

import java.util.HashSet;
import java.util.Set;

public class AddCityActivity extends AppCompatActivity implements View.OnClickListener, AsyncFindCity.OnCompleteListener{

    private EditText etCityName;
    private ProgressBar progressBar;
    private Button btnSearch, btnCancel;
    private RecyclerView rvCity;
    private FoundedCityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        progressBar = (ProgressBar) findViewById(R.id.pb_activity_add);

        etCityName = (EditText) findViewById(R.id.et_activity_add_city);
        etCityName.setOnClickListener(this);

        btnSearch = (Button) findViewById(R.id.btn_activity_add_city_search);
        btnSearch.setOnClickListener(this);

        btnCancel = (Button) findViewById(R.id.btn_activity_add_city_cancel);
        btnCancel.setOnClickListener(this);

        rvCity = (RecyclerView) findViewById(R.id.rv_activity_add_city_founded);
        adapter = new FoundedCityAdapter(this);

        rvCity.setAdapter(adapter);
        rvCity.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_activity_add_city_search:
                setProgress(true);
                String name = etCityName.getText().toString();
                AsyncFindCity asyncFindCity = new AsyncFindCity(this);
                asyncFindCity.setListener(this);
                asyncFindCity.execute(name);

            break;
            case R.id.btn_activity_add_city_cancel:
                finish();
                break;
            case R.id.et_activity_add_city:
                etCityName.setTextColor(getResources().getColor(R.color.colorEditTextText));
                break;
        }
    }



    private void  showError (String message) {
        Toast.makeText(AddCityActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private void setProgress(boolean progress) {
        if (progress) {
            etCityName.setVisibility(View.GONE);
            btnSearch.setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
            rvCity.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            etCityName.setVisibility(View.VISIBLE);
            btnSearch.setVisibility(View.VISIBLE);
            btnCancel.setVisibility(View.VISIBLE);
            rvCity.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public void onComplete(Set<City> cities) {
        setProgress(false);
        if (cities == null) {
            this.showError("Not found!");
        } else {
            if (cities.size() == 0) {
                this.showError("Not found!");
            }
            adapter.setList(SetToListConvertor.setToList(cities));
        }

    }
}
