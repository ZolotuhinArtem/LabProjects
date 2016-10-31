package com.example.zolotuhinartem.contactlist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

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
        try {
            onContactClickListener = (OnContactClickListener) context;
        } catch (ClassCastException e) {
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

    // --------------------------ADD and DELETE number-------------------------------
    public void addContact(Contact contact) {
        if (dataNumberList != null) {
            dataNumberList.add(contact);
            List<Contact> list = dataNumberList.getAll();
            contactAdapter.setList(list);
        }
    }

    public void deleteNumber(Contact contact) {
        if (contactAdapter != null) {
            List<Contact> list = contactAdapter.getList();
            list.remove(contact);
            if (dataNumberList != null) {
                dataNumberList.remove(contact);
            }
            contactAdapter.setList(list);

        }
    }

    public void restoreNumber(Contact contact) {
        if (contactAdapter != null) {
            this.deleteNumber(contact);
            ContactViewPagerFragment contactViewPagerFragment = (ContactViewPagerFragment) getActivity()
                    .getSupportFragmentManager()
                    .findFragmentByTag(ContactViewPagerFragment.class.getName());

            if (contactViewPagerFragment != null) {
                ContactFragment contactFragment = contactViewPagerFragment.getContactFragment();
                if (contactFragment != null) {
                    contactFragment.addContact(contact);
                }
            }
        }
    }

    //---------------------------------------------------------------------------------------
    //LISTENER
    @Override
    public void onContactClick(Contact contact) {
        onContactLongClick(contact);
    }

    @Override
    public void onContactLongClick(final Contact contact) {
        ContactDialogRestoreOrDelete dialog = new ContactDialogRestoreOrDelete();
        dialog.setOnDialogClickListener(new ContactDialogRestoreOrDelete.OnDialogClickListener() {
            @Override
            public void onClick(int status) {
                switch (status) {
                    case ContactDialogRestoreOrDelete.CLICK_RESTORE:
                        restoreNumber(contact);
                        break;
                    case ContactDialogRestoreOrDelete.CLICK_DELETE:
                        deleteNumber(contact);
                        break;
                }
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString(ContactDialogRestoreOrDelete.MESSAGE, contact.getNumber());
        dialog.setArguments(bundle);

        dialog.show(getActivity().getSupportFragmentManager(), ContactDialogRestoreOrDelete.class.getName());
    }
}
