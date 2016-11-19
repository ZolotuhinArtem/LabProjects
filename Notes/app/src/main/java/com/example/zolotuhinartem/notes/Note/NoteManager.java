package com.example.zolotuhinartem.notes.Note;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zolotuhinartem on 19.11.16.
 */

public class NoteManager {

    private static final String ATTRIBUTE_HEAD = "head";
    private static final String ATTRIBUTE_BODY = "body";



    public static JSONObject toJson(Note note){
        if (note != null) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(ATTRIBUTE_HEAD, note.getHead());
                jsonObject.put(ATTRIBUTE_BODY, note.getBody());
                return jsonObject;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Note fromJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                String head = jsonObject.getString(ATTRIBUTE_HEAD);
                String body = jsonObject.getString(ATTRIBUTE_BODY);
                if ((head != null) && (body != null)) {
                    return new Note(head, body);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
