package com.example.zolotuhinartem.contactlist;

import android.content.Intent;

/**
 * Created by zolotuhinartem on 15.10.16.
 */

public class Contact {
    public static final String KEY_NUMBER = "number";
    private String number;

    public Contact(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
    public void fillIntent(Intent intent){
        intent.putExtra(KEY_NUMBER, this.number);
    }
    public static Contact getFromIntent(Intent intent){
        return new Contact(intent.getStringExtra(KEY_NUMBER));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return number != null ? number.equals(contact.number) : contact.number == null;

    }


}
