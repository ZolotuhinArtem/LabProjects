package com.example.zolotuhinartem.notes.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.zolotuhinartem.notes.Note.Note;
import com.example.zolotuhinartem.notes.R;
import com.example.zolotuhinartem.notes.TaskFragments.Edit.EditAsyncTaskFragment;
import com.example.zolotuhinartem.notes.TaskFragments.Edit.EditResultListener;

public class EditActivity extends AppCompatActivity implements EditResultListener, View.OnClickListener{


    public static final int RESULT_SAVE = 1;
    public static final int RESULT_CANCEL = 2;

    public static final String OLD_NOTE_HEAD = "oldnotehead";
    public static final String OLD_NOTE_BODY = "oldnotebody";
    public static final String NEW_NOTE_HEAD = "newnotehead";
    public static final String NEW_NOTE_BODY = "newnotebody";

    private EditText etHead, etBody;
    private Button btnSave, btnCancel;
    private ProgressBar progressBar;

    private Intent intent;

    private Note oldNote;

    private EditAsyncTaskFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etHead = (EditText) findViewById(R.id.et_head);
        etBody = (EditText) findViewById(R.id.et_body);

        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.pb_edit);

        intent = getIntent();
        if (savedInstanceState == null) {
            String head = intent.getStringExtra(OLD_NOTE_HEAD);
            String body = intent.getStringExtra(OLD_NOTE_BODY);
            oldNote = new Note(head, body);
            etHead.setText(head);
            etBody.setText(body);
        } else {
            loadSavedInstanceState(savedInstanceState);
        }

        fragment = (EditAsyncTaskFragment) getFragmentManager().findFragmentByTag(EditAsyncTaskFragment.class.getName());

        if (fragment == null) {
            fragment = new EditAsyncTaskFragment();
            getFragmentManager().beginTransaction().add(fragment, EditAsyncTaskFragment.class.getName()).commit();
        }

        setProgress(fragment.isWorking());

    }

    private void loadSavedInstanceState(Bundle savedInstanceState) {
        String head = savedInstanceState.getString(OLD_NOTE_HEAD);
        String body = savedInstanceState.getString(OLD_NOTE_BODY);
        this.oldNote = new Note(head, body);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(OLD_NOTE_HEAD, oldNote.getHead());
        outState.putString(OLD_NOTE_BODY, oldNote.getBody());
        outState.putString(NEW_NOTE_HEAD, etHead.getText().toString());
        outState.putString(NEW_NOTE_BODY, etBody.getText().toString());
    }



    @Override
    public void onEditResult(Note note) {
        setProgress(false);
        if (note != null) {
            intent.putExtra(OLD_NOTE_HEAD, oldNote.getHead());
            intent.putExtra(OLD_NOTE_BODY, oldNote.getBody());
            intent.putExtra(NEW_NOTE_HEAD, note.getHead());
            intent.putExtra(NEW_NOTE_BODY, note.getBody());
            setResult(RESULT_SAVE, intent);
            finish();
        }
    }

    public void setProgress(boolean b){
        if (b) {
            etHead.setVisibility(View.GONE);
            etBody.setVisibility(View.GONE);
            btnSave.setClickable(false);
            btnCancel.setClickable(false);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            etHead.setVisibility(View.VISIBLE);
            etBody.setVisibility(View.VISIBLE);
            btnSave.setClickable(true);
            btnCancel.setClickable(true);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                fragment.execute(new Note(etHead.getText().toString(), etBody.getText().toString()));
                setProgress(true);
                break;
            case R.id.btn_cancel:
                setResult(RESULT_CANCEL);
                finish();
                break;
        }
    }
}
