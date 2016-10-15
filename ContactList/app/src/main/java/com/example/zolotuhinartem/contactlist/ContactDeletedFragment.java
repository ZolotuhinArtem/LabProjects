package com.example.zolotuhinartem.contactlist;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zolotuhinartem on 15.10.16.
 */

public class ContactDeletedFragment extends Fragment implements ContactOnClickListener{

    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deleted_contacts, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doViewCreated(ContactFragment.NUMBERS_DELETED_KEY, R.id.rv_fragment_deleted_contacts_list, view, savedInstanceState, false);

    }

    public List<Contact> generateNumbersList(int size) {
        ArrayList<Contact> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(new Contact(generateNumber(11)));
        }
        return list;
    }

    public String generateNumber(int size) {
        StringBuilder result = new StringBuilder(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            result.append(Integer.toString(random.nextInt() % 10));
        }
        return result.toString();
    }

    protected void doViewCreated(String dataArrayName, int listId, View view, @Nullable Bundle savedInstanceState, boolean isGenerateList) {
        recyclerView = (RecyclerView) view.findViewById(listId);
        Activity activity = getActivity();
        if (activity != null) {
            SharedPreferences sharedPreferences = activity.getSharedPreferences(ContactFragment.NUMBER_LIST_KEY, Context.MODE_PRIVATE);
            List<Contact> list = JSONUtils.getContactListFromSharedPreferences(sharedPreferences, dataArrayName);
            if (isGenerateList) {
                if (list == null) {
                    list = generateNumbersList(25);
                    JSONUtils.saveContactListIntoSharedPreferences(list, sharedPreferences, dataArrayName);
                } else {
                    if (list.size() == 0) {
                        list = generateNumbersList(25);
                        JSONUtils.saveContactListIntoSharedPreferences(list, sharedPreferences, dataArrayName);
                    }
                }
            }

            if (list == null) {
                list = new ArrayList<>();
            }
            ContactAdapter adapter = new ContactAdapter();
            adapter.setList(list);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            adapter.setContactOnClickListener(this);
        }
    }

    //LISTENER
    @Override
    public void onClick(Contact contact) {

    }

    @Override
    public void onLongClick(Contact contact) {

    }
}
