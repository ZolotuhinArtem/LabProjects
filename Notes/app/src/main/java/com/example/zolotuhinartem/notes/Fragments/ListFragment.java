package com.example.zolotuhinartem.notes.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zolotuhinartem.notes.Note.Note;
import com.example.zolotuhinartem.notes.R;
import com.example.zolotuhinartem.notes.recylerview.NoteAdapter;
import com.example.zolotuhinartem.notes.recylerview.NoteOnClickListener;
import com.example.zolotuhinartem.notes.repositories.NoteRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zolotuhinartem on 19.11.16.
 */

public class ListFragment extends Fragment implements NoteOnClickListener{


    public static final String DATA_NOTE = "notes";
    public static final int LIST_SIZE = 25;

    private Context context;

    private NoteAdapter adapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.list_fragment, container, false);
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_notes_list);
        adapter = new NoteAdapter();
        adapter.setListener(this);

        NoteRepository repository = new NoteRepository(getActivity().getSharedPreferences(DATA_NOTE, getActivity().MODE_PRIVATE));
        List<Note> list = repository.getAll();

        if (list == null) {
            list = generateList(LIST_SIZE);
            repository.replaceOnNewList(list);
        } else {
            if (list.size() == 0){
                list = generateList(LIST_SIZE);
                repository.replaceOnNewList(list);
            }
        }

        adapter.setList(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private List<Note> generateList(int size){
        List<Note> list = new LinkedList<>();

        for(int i = 0; i < size; i++) {
            list.add(new Note("Head: " + i, "Text: продам машину"));
        }

        return list;

    }

    public void setList(List<Note> list) {
        if ((this.adapter != null) && (list != null)) {
            adapter.setList(list);
        }
    }

    @Override
    public void onNoteClick(Note note) {
        try {
            NoteOnClickListener listener = (NoteOnClickListener) getActivity();
            listener.onNoteClick(note);
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }
}
