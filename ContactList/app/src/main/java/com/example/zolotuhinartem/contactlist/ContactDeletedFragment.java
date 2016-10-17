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

public class ContactDeletedFragment extends Fragment implements ContactOnClickListener {

    private RecyclerView recyclerView;
    private OnContactClickListener onContactClickListener;
    private DataNumberList dataNumberList;
    private ContactAdapter contactAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            onContactClickListener = (OnContactClickListener) context;
        }catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnContactClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deleted_contacts, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doViewCreated(ContactFragment.NUMBERS_DELETED_KEY, R.id.rv_fragment_deleted_contacts_list, view, savedInstanceState);

    }



    protected void doViewCreated(String dataArrayName, int listId, View view, @Nullable Bundle savedInstanceState) {

        recyclerView = (RecyclerView) view.findViewById(listId);
        Activity activity = getActivity();
        if (activity != null) {
            dataNumberList = new JSONDataNumberList(activity.getSharedPreferences(ContactFragment.NUMBER_LIST_KEY, Context.MODE_PRIVATE), dataArrayName);

            List<Contact> list = dataNumberList.getAll();
            contactAdapter = new ContactAdapter();
            contactAdapter.setList(list);
            recyclerView.setAdapter(contactAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            contactAdapter.setContactOnClickListener(this);
        }
    }
    public void addContact(Contact contact){
        if (dataNumberList != null){
            dataNumberList.add(contact);
            List<Contact> list = dataNumberList.getAll();
            contactAdapter.setList(list);
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
