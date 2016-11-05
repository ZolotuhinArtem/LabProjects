package com.example.zolotuhinartem.simpleweather.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zolotuhinartem.simpleweather.R;
import com.example.zolotuhinartem.simpleweather.asynctasks.AsyncInitilization;

public class InitilizationActivity extends AppCompatActivity implements AsyncInitilization.OnCompleteInitilizationListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initilization);
        AsyncInitilization asyncInitilization = new AsyncInitilization();
        asyncInitilization.setListener(this);
        asyncInitilization.execute(this);
    }

    @Override
    public void onCompleteInitilization(Boolean bool) {
        Intent intent = new Intent(InitilizationActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
