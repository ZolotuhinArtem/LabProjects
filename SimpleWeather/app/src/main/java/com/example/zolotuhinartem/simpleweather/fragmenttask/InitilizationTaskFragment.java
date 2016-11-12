package com.example.zolotuhinartem.simpleweather.fragmenttask;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.zolotuhinartem.simpleweather.fragmenttask.callback.InitilizationCallback;
import com.example.zolotuhinartem.simpleweather.repositories.CityRepository;

/**
 * Created by zolotuhinartem on 12.11.16.
 */

public class InitilizationTaskFragment extends Fragment {

    private InitilizationCallback initilizationCallback;
    private InitilizationAsyncTask initilizationAsyncTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.initilizationCallback = null;
    }

    private void attach(Context context) {
        try {
            this.initilizationCallback = (InitilizationCallback) context;
        } catch (ClassCastException ex) {

        }
    }

    public boolean isWorking() {
        return this.initilizationAsyncTask != null;
    }

    public void execute(Context context) {
        if (initilizationAsyncTask == null) {
            initilizationAsyncTask = new InitilizationAsyncTask();
            initilizationAsyncTask.execute(context);
        }
    }

    public class InitilizationAsyncTask extends AsyncTask<Context, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Context... contexts) {
            if (contexts != null) {
                if (contexts.length > 0) {
                    if (contexts[0] != null) {
                        CityRepository.init(contexts[0]);
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            initilizationAsyncTask = null;
            if (initilizationCallback != null) {
                initilizationCallback.resultInitilization(aBoolean);
            }
        }
    }
}
