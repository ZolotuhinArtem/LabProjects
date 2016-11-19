package com.example.zolotuhinartem.notes.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zolotuhinartem.notes.Activities.MainActivity;
import com.example.zolotuhinartem.notes.Note.Note;
import com.example.zolotuhinartem.notes.R;

/**
 * Created by zolotuhinartem on 19.11.16.
 */

public class ShowFragment extends Fragment implements View.OnClickListener {


    private Note note;

    private TextView tvHead;
    private TextView tvBody;

    private Button btnEdit;

    private EditClickListener listener;


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

    private void attach(Context context){
        try {
            this.listener = (EditClickListener) context;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            setNote(((MainActivity) getActivity()).getCurrentNote());
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_fragment, container, false);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvHead = (TextView) view.findViewById(R.id.tv_show_head);
        tvBody = (TextView) view.findViewById(R.id.tv_show_body);
        btnEdit = (Button) view.findViewById(R.id.btn_ediit);
        btnEdit.setOnClickListener(this);
        try {
            this.setNote(((MainActivity)getActivity()).getCurrentNote());
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }

    }

    public void updateTextViews(){
        if (this.note != null) {
            if ((tvBody != null) && (tvHead != null)) {
                tvHead.setText(note.getHead());
                tvBody.setText(note.getBody());
            }
        }
    }

    public void setNote(Note note){
        this.note = note;
        updateTextViews();

    }

    public Note getNote() {
        return note;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onEditClick(note);
        }

    }

    public interface EditClickListener{
        void onEditClick(Note note);
    }
}
