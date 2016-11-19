package com.example.zolotuhinartem.notes.TaskFragments.Splash;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

/**
 * Created by zolotuhinartem on 19.11.16.
 */

public class SplashAsyncTaskFragment extends Fragment{

    private SplashResultListener listener;
    private SplashAsyncTask asyncTask;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public void onAttach(Context context) {
        attach(context);
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        attach(activity);
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        this.listener = null;
        super.onDetach();
    }

    public void execute(){
        if (!isWorking()) {
            asyncTask = new SplashAsyncTask();
            asyncTask.execute();
        }
    }

    public boolean isWorking(){
        return asyncTask != null;
    }

    private void attach(Context context){
        try {
            this.listener = (SplashResultListener) context;
        } catch (ClassCastException ex) {
            //
        }
    }


    private class SplashAsyncTask extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (listener != null){
                listener.onSplashResult(aBoolean);
            }
            asyncTask = null;
        }
    }

}
