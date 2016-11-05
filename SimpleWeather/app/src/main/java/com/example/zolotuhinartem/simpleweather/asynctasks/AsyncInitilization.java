package com.example.zolotuhinartem.simpleweather.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.example.zolotuhinartem.simpleweather.repositories.CityRepository;

/**
 * Created by zolotuhinartem on 04.11.16.
 */

public class AsyncInitilization extends AsyncTask<Context, Void, Boolean> {

    private OnCompleteInitilizationListener listener;

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
        if (listener != null) {
            listener.onCompleteInitilization(aBoolean);
        }
    }

    public void setListener(OnCompleteInitilizationListener listener) {
        this.listener = listener;
    }

    public interface OnCompleteInitilizationListener {
        void onCompleteInitilization(Boolean bool);
    }
}
