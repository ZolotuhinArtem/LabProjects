package com.example.zolotuhinartem.notes.recylerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.zolotuhinartem.notes.Note.Note;
import com.example.zolotuhinartem.notes.R;

/**
 * Created by zolotuhinartem on 19.11.16.
 */

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private TextView tvHead;
    private TextView tvBody;


    public NoteViewHolder(View itemView) {
        super(itemView);
        tvHead = (TextView) itemView.findViewById(R.id.tv_note_item_head);
        tvBody = (TextView) itemView.findViewById(R.id.tv_note_item_body);
    }
    public void bind(final Note note, final NoteOnClickListener listener){
        if (note != null) {
            this.tvHead.setText(note.getHead());
            this.tvBody.setText(note.getBody());
            if (listener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onNoteClick(note);
                    }
                });
            }
        }
    }
}
