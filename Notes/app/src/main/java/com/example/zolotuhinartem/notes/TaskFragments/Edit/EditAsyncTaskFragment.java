package com.example.zolotuhinartem.notes.TaskFragments.Edit;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.zolotuhinartem.notes.Note.Note;

/**
 * Created by zolotuhinartem on 19.11.16.
 */

public class EditAsyncTaskFragment extends Fragment {

    private EditResultListener listener;
    private EditAsyncTask asyncTask;


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

    public void execute(Note note) {
        if (!isWorking()) {
            if (note != null) {
                asyncTask = new EditAsyncTask();
                asyncTask.execute(note);
            }
        }
    }

    private void attach(Context context) {
        try {
            this.listener = (EditResultListener) context;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isWorking(){
        return asyncTask != null;
    }

    public class EditAsyncTask extends AsyncTask<Note, Void, Note>{


        @Override
        protected Note doInBackground(Note... notes) {
            if (notes != null) {
                if (notes[0] != null) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return notes[0];
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Note note) {
            asyncTask = null;
            if (listener != null) {
                listener.onEditResult(note);
            }
        }
    }

}
