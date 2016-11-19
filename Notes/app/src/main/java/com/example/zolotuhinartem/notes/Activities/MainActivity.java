package com.example.zolotuhinartem.notes.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zolotuhinartem.notes.Fragments.ListFragment;
import com.example.zolotuhinartem.notes.Fragments.ShowFragment;
import com.example.zolotuhinartem.notes.Note.Note;
import com.example.zolotuhinartem.notes.R;
import com.example.zolotuhinartem.notes.recylerview.NoteAdapter;
import com.example.zolotuhinartem.notes.recylerview.NoteOnClickListener;
import com.example.zolotuhinartem.notes.repositories.NoteRepository;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteOnClickListener, ShowFragment.EditClickListener{

    public static final String PARAM_NOTE_BODY = "paramnotebody";
    public static final String PARAM_NOTE_HEAD = "paramnotehead";


    private ListFragment listFragment;
    private ShowFragment showFragment;
    private Note currentNote;

    private boolean isLandscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isLandscape = false;

        FragmentManager fragmentManager = getFragmentManager();
        listFragment = getListFragment();
        fragmentManager.beginTransaction().replace(R.id.list_show_fragment_container, listFragment).commit();

        currentNote = null;
        if (savedInstanceState != null) {
            String head = savedInstanceState.getString(PARAM_NOTE_HEAD);
            String body = savedInstanceState.getString(PARAM_NOTE_BODY);
            if((head != null) && (body != null)) {
                currentNote = new Note(head, body);
            }
        }

        if (findViewById(R.id.show_fragment_container) != null) {
            showFragment = getShowFragment();
            if (currentNote != null) {
                showFragment.setNote(currentNote);
            }

            fragmentManager.beginTransaction().replace(R.id.show_fragment_container, showFragment).commit();
            isLandscape = true;
        } else {
            if (currentNote != null) {
                showFragment = getShowFragment();
                showFragment.setNote(currentNote);
                getFragmentManager().beginTransaction().replace(R.id.list_show_fragment_container, showFragment)
                        .addToBackStack(null).commit();
            }
        }



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if ((currentNote != null)){
            if (currentNote != null) {
                outState.putString(PARAM_NOTE_HEAD, currentNote.getHead());
                outState.putString(PARAM_NOTE_BODY, currentNote.getBody());
            }
        }
    }

    @Override
    public void onNoteClick(Note note) {
        currentNote = note;
        if (isLandscape) {
            showFragment.setNote(note);

        } else {
            showFragment = getShowFragment();
            showFragment.setNote(note);
            getFragmentManager().beginTransaction().replace(R.id.list_show_fragment_container, showFragment)
                    .addToBackStack(null).commit();
        }
    }

    public ShowFragment getShowFragment() {
        ShowFragment result = (ShowFragment) getFragmentManager().findFragmentByTag(ShowFragment.class.getName());
        if (result == null) {
            result = new ShowFragment();
        }
        return result;
    }

    public ListFragment getListFragment() {
        ListFragment result = (ListFragment) getFragmentManager().findFragmentByTag(ListFragment.class.getName());
        if (result == null) {
            result = new ListFragment();
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        currentNote = null;
        super.onBackPressed();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (resultCode == (EditActivity.RESULT_SAVE)) {
                    String head = data.getStringExtra(EditActivity.NEW_NOTE_HEAD);
                    String body = data.getStringExtra(EditActivity.NEW_NOTE_BODY);
                    currentNote = new Note(head, body);
                    head = data.getStringExtra(EditActivity.OLD_NOTE_HEAD);
                    body = data.getStringExtra(EditActivity.OLD_NOTE_BODY);
                    final NoteRepository repository = new NoteRepository(getSharedPreferences(ListFragment.DATA_NOTE, MODE_PRIVATE));
                    repository.remove(new Note(head, body));
                    repository.add(currentNote);
                    showFragment = getShowFragment();
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            showFragment.setNote(currentNote);
                            listFragment.setList(repository.getAll());
                        }
                    });
                }
                break;

        }
    }

    public Note getCurrentNote() {
        return currentNote;
    }

    @Override
    public void onEditClick(Note note) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(EditActivity.OLD_NOTE_HEAD, note.getHead());
        intent.putExtra(EditActivity.OLD_NOTE_BODY, note.getBody());
        startActivityForResult(intent, 0);
    }
}
