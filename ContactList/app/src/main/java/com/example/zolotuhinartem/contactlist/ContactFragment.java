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
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

/**
 * Created by zolotuhinartem on 15.10.16.
 */

public class ContactFragment extends Fragment implements ContactOnClickListener {
    public static final String NUMBER_LIST_KEY = "numberListKey";
    public static final String NUMBERS_KEY = "numbers";
    public static final String NUMBERS_DELETED_KEY = "deletednumbers";

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private Button btnClearAll, btnGenerate, btnAdd;
    private OnContactClickListener onContactClickListener;
    private DataNumberList dataNumberList;

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
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        btnClearAll = (Button) view.findViewById(R.id.btn_fragment_contacts_clear_all);
        btnGenerate = (Button) view.findViewById(R.id.btn_fragment_contacts_generate);
        btnAdd = (Button) view.findViewById(R.id.btn_fragment_contacts_add);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doViewCreated(NUMBERS_KEY, R.id.rv_fragment_contacts_list, view, savedInstanceState);


    }

    protected void doViewCreated(final String dataArrayName, int listId, View view, @Nullable Bundle savedInstanceState) {

        final Activity activity = getActivity();

        if (activity != null) {
            final int randomSize = 25;
            dataNumberList = new JSONDataNumberList(activity.getSharedPreferences(NUMBER_LIST_KEY, Context.MODE_PRIVATE), dataArrayName);
            contactAdapter = new ContactAdapter();

            List<Contact> list = dataNumberList.getAll();

            recyclerView = (RecyclerView) view.findViewById(listId);
            contactAdapter.setList(list);
            recyclerView.setAdapter(contactAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            contactAdapter.setContactOnClickListener(this);
            /*
            *-------------------BUTTONS-----------------------------
             */
            btnGenerate.setText(new String(getString(R.string.add) + " " + randomSize + " " + getString(R.string.random)));
            btnClearAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataNumberList.clear();
                    contactAdapter.setList(dataNumberList.getAll());

                }
            });
            btnGenerate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<Contact> list = ContactListGenerator.generateNumbersList(randomSize);
                    dataNumberList.addList(list);
                    contactAdapter.setList(dataNumberList.getAll());
                }
            });
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickOnButtonAdd();
                }
            });
            //-------------------------------------------------------
        }
    }

    // ADD and DELETE number
    public void deleteNumber(Contact contact) {
        if (contactAdapter != null) {
            List<Contact> list = contactAdapter.getList();
            list.remove(contact);
            if (dataNumberList != null) {
                dataNumberList.remove(contact);
            }
            contactAdapter.setList(list);
            ContactViewPagerFragment contactViewPagerFragment = (ContactViewPagerFragment) getActivity()
                    .getSupportFragmentManager()
                    .findFragmentByTag(ContactViewPagerFragment.class.getName());

            if (contactViewPagerFragment != null) {
                ContactDeletedFragment contactDeletedFragment = contactViewPagerFragment.getContactDeletedFragment();
                if (contactDeletedFragment != null) {
                    contactDeletedFragment.addContact(contact);
                }
            }
        }
    }

    public void addContact(Contact contact) {
        if (dataNumberList != null) {
            dataNumberList.add(contact);
            List<Contact> list = dataNumberList.getAll();
            contactAdapter.setList(list);
        }
    }
    //--------------------------------------

    //LISTENER
    @Override
    public void onContactClick(Contact contact) {
        if (onContactClickListener != null) {
            onContactClickListener.onContactClick(contact);
        }
    }

    @Override
    public void onContactLongClick(final Contact contact) {
        ContactDialogDelete dialog = new ContactDialogDelete();

        dialog.setOnDialogClickListener(new ContactDialogDelete.OnDialogClickListener() {
            @Override
            public void onClick(int status) {
                switch (status) {
                    case ContactDialogDelete.CLICK_YES:
                        deleteNumber(contact);
                        break;
                    case ContactDialogDelete.CLICK_NO:
                        break;
                    default:
                        break;
                }
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString(ContactDialogDelete.MESSAGE, contact.getNumber());
        dialog.setArguments(bundle);

        dialog.show(getActivity().getSupportFragmentManager(), ContactDialogDelete.class.getName());

    }

    public void clickOnButtonAdd() {
        ContactDialogAdd dialogAdd = new ContactDialogAdd();

        dialogAdd.setListener(new ContactDialogAdd.OnDialogClickListener() {
            @Override
            public void onClick(int status, String number) {
                switch (status) {
                    case ContactDialogAdd.CLICK_ADD:
                        if (ValidDataChecker.checkNumber(number)) {
                            addContact(new Contact(number));
                        } else {
                            Activity activity = getActivity();
                            if (activity != null) {
                                Toast.makeText(activity, R.string.invalid_number, Toast.LENGTH_LONG).show();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        dialogAdd.show(getActivity().getSupportFragmentManager(), ContactDialogAdd.class.getName());

    }
}
