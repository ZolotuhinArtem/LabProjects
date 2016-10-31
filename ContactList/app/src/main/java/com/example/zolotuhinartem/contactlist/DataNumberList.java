package com.example.zolotuhinartem.contactlist;


import java.util.List;

/**
 * Created by zolotuhinartem on 17.10.16.
 */

public interface DataNumberList {
    List<Contact> getAll();

    void addList(List<Contact> list);

    void add(Contact contact);

    void clear();

    void remove(Contact contact);
}
