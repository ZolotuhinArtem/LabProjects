package com.example.zolotuhinartem.simpleweather.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zolotuhinartem.simpleweather.R;
import com.example.zolotuhinartem.simpleweather.fragmenttask.InitilizationTaskFragment;
import com.example.zolotuhinartem.simpleweather.fragmenttask.callback.InitilizationCallback;

public class InitilizationActivity extends AppCompatActivity implements InitilizationCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initilization);

        FragmentManager fragmentManager = getFragmentManager();

        InitilizationTaskFragment initilizationTaskFragment = (InitilizationTaskFragment) fragmentManager
                .findFragmentByTag(InitilizationTaskFragment.class.getName());

        if (initilizationTaskFragment == null) {
            initilizationTaskFragment = new InitilizationTaskFragment();
            fragmentManager.beginTransaction().add(initilizationTaskFragment, InitilizationTaskFragment.class.getName()).commit();
        }

        initilizationTaskFragment.execute(this);
    }

    @Override
    public void resultInitilization(Boolean bool) {
        Intent intent = new Intent(InitilizationActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
