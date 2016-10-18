package com.example.zolotuhinartem.contactlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by zolotuhinartem on 15.10.16.
 */

public class ContactViewHolder extends RecyclerView.ViewHolder {

    TextView tvNumber;

    public ContactViewHolder(View itemView) {
        super(itemView);
        tvNumber = (TextView) itemView.findViewById(R.id.tv_number_item_number);
    }

    public void onBind(final Contact contact, final ContactOnClickListener contactOnClickListener) {
        String text = "";
        if (contact != null) {
            text = contact.getNumber();
        }
        tvNumber.setText(text);
        if (contactOnClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contactOnClickListener.onContactClick(contact);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    contactOnClickListener.onContactLongClick(contact);
                    return true;
                }
            });
        }
    }
}
