package com.example.zolotuhinartem.notes.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zolotuhinartem.notes.R;
import com.example.zolotuhinartem.notes.TaskFragments.Splash.SplashAsyncTaskFragment;
import com.example.zolotuhinartem.notes.TaskFragments.Splash.SplashResultListener;

public class SplashActivity extends AppCompatActivity implements SplashResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FragmentManager fragmentManager = getFragmentManager();

        SplashAsyncTaskFragment splashAsyncTaskFragment = (SplashAsyncTaskFragment) fragmentManager.findFragmentByTag(SplashAsyncTaskFragment.class.getName());

        if (splashAsyncTaskFragment == null) {
            splashAsyncTaskFragment = new SplashAsyncTaskFragment();
            fragmentManager.beginTransaction().add(splashAsyncTaskFragment, SplashAsyncTaskFragment.class.getName()).commit();
            splashAsyncTaskFragment.execute();
        }

    }

    @Override
    public void onSplashResult(Boolean b) {
        if(b){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
