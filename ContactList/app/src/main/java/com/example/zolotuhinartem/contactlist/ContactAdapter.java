package com.example.zolotuhinartem.contactlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zolotuhinartem on 15.10.16.
 */

public class ContactAdapter extends android.support.v7.widget.RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Contact> list;
    private ContactOnClickListener contactOnClickListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContactViewHolder){
            Contact contact = this.list.get(position);
            ((ContactViewHolder) holder).onBind(contact, this.contactOnClickListener);

        }
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public void setList(List<Contact> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<Contact> getList() {
        return list;
    }

    public void setContactOnClickListener(ContactOnClickListener contactOnClickListener) {
        this.contactOnClickListener = contactOnClickListener;
    }
}
