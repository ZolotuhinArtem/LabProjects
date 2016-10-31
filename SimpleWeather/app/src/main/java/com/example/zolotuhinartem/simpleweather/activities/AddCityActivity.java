package com.example.zolotuhinartem.simpleweather.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zolotuhinartem.simpleweather.R;
import com.example.zolotuhinartem.simpleweather.asynctasks.AsyncRequestWeather;
import com.example.zolotuhinartem.simpleweather.objects.City;
import com.example.zolotuhinartem.simpleweather.objects.utils.CityIntentManager;
import com.example.zolotuhinartem.simpleweather.weather.WeatherResponse;
import com.example.zolotuhinartem.simpleweather.weather.pojo.Weather;

public class AddCityActivity extends AppCompatActivity implements View.OnClickListener, AsyncRequestWeather.OnCompleteListener{

    private EditText etCityName, etCountryName;
    private ProgressBar progressBar;
    private Button btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        progressBar = (ProgressBar) findViewById(R.id.pb_activity_add);

        etCityName = (EditText) findViewById(R.id.et_activity_add_city);
        etCityName.setOnClickListener(this);

        etCountryName = (EditText) findViewById(R.id.et_activity_add_country);
        etCountryName.setOnClickListener(this);

        btnAdd = (Button) findViewById(R.id.btn_activity_add_city_add);
        btnAdd.setOnClickListener(this);

        btnCancel = (Button) findViewById(R.id.btn_activity_add_city_cancel);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_activity_add_city_add:
                City city = getCity();
                if (city != null) {
                    AsyncRequestWeather asyncRequestWeather = new AsyncRequestWeather(this);
                    asyncRequestWeather.execute(city);
                    setProgress(true);
                } else {
                    showError("City name invalid");
                }

            break;
            case R.id.btn_activity_add_city_cancel:
                finish();
                break;
            case R.id.et_activity_add_city:
                etCityName.setTextColor(getResources().getColor(R.color.colorEditTextText));
                break;
            case R.id.et_activity_add_country:
                etCountryName.setTextColor(getResources().getColor(R.color.colorEditTextText));
                break;
        }
    }

    private City getCity(){
        String cityName = etCityName.getText().toString();
        String countryName = etCountryName.getText().toString();
        if ((cityName != null) && (countryName != null)){
            if ((!cityName.equals("")) && (!countryName.equals(""))){
                return  new City(cityName, countryName);
            }
        }

        return null;
    }

    private void  showError (String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void setProgress(boolean progress) {
        if (progress) {
            etCityName.setVisibility(View.GONE);
            etCountryName.setVisibility(View.GONE);
            btnAdd.setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            etCityName.setVisibility(View.VISIBLE);
            etCountryName.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.VISIBLE);
            btnCancel.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onComplete(WeatherResponse weatherResponse) {
        setProgress(false);
        int code = weatherResponse.getCode();
        if (code <= 399) {
            Weather weather = weatherResponse.getWeather();
            if (weather != null) {
                Intent intent = getIntent();
                intent = CityIntentManager.fill(intent, weatherResponse.getCity());
                setResult(1, intent);
                finish();
            } else {
                etCityName.setTextColor(getResources().getColor(R.color.colorAccent));
                etCountryName.setTextColor(getResources().getColor(R.color.colorAccent));
                showError("Error! Code: " + code);
            }
        } else {
            etCityName.setTextColor(getResources().getColor(R.color.colorAccent));
            etCountryName.setTextColor(getResources().getColor(R.color.colorAccent));
            showError("Error! Code: " + code);
        }

    }
}
