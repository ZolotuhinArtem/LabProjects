package com.example.zolotuhinartem.notes.repositories;

import android.content.SharedPreferences;

import com.example.zolotuhinartem.notes.Note.Note;
import com.example.zolotuhinartem.notes.Note.NoteManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zolotuhinartem on 19.11.16.
 */

public class NoteRepository {


    public static final String NOTE_DATA_NAME = "notes";

    private SharedPreferences sharedPreferences;

    public NoteRepository(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    public List<Note> getAll(){
        try {
            JSONArray jsonArray = new JSONArray(sharedPreferences.getString(NOTE_DATA_NAME, "[]"));
            List<Note> result = new LinkedList<>();
            JSONObject note;
            for(int i = 0; i < jsonArray.length(); i++){
                note = jsonArray.getJSONObject(i);
                result.add(NoteManager.fromJson(note));
            }
            return result;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(){
        this.sharedPreferences.edit().putString(NOTE_DATA_NAME, "[]").commit();
    }

    public void remove(Note note){
        List<Note> list = getAll();
        list.remove(note);
        replaceOnNewList(list);
    }

    public boolean add(Note note) {
        if (note != null) {
            List<Note> list = getAll();

            if (list == null) {
                list = new LinkedList<>();
            }

            list.add(note);
            replaceOnNewList(list);
        }
        return false;
    }

    public boolean replaceOnNewList(List<Note> list) {
        if (list != null) {
            JSONArray jsonArray = new JSONArray();
            for(Note i: list) {
                jsonArray.put(NoteManager.toJson(i));
            }
            this.sharedPreferences.edit().putString(NOTE_DATA_NAME, jsonArray.toString()).commit();
        }
        return false;


    }


}
