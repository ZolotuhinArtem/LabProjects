package com.example.zolotuhinartem.notes.Note;

/**
 * Created by zolotuhinartem on 19.11.16.
 */

public class Note {




    private String head;
    private String body;

    public Note(String head, String body) {
        this.head = head;
        this.body = body;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (head != null ? !head.equals(note.head) : note.head != null) return false;
        return body != null ? body.equals(note.body) : note.body == null;

    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }
}
