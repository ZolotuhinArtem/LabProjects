package com.example.zolotuhinartem.notes.recylerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zolotuhinartem.notes.Note.Note;
import com.example.zolotuhinartem.notes.R;

import java.util.List;

/**
 * Created by zolotuhinartem on 19.11.16.
 */

public class NoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Note> list;
    private NoteOnClickListener listener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Note note = list.get(position);
        if (note != null) {
            if (holder instanceof NoteViewHolder) {
                ((NoteViewHolder) holder).bind(note, this.listener);
            }
        }

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public void setList(List<Note> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setListener(NoteOnClickListener listener) {
        this.listener = listener;
    }
}
