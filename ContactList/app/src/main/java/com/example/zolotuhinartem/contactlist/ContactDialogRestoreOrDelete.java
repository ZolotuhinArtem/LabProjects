package com.example.zolotuhinartem.contactlist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by zolotuhinartem on 18.10.16.
 */

public class ContactDialogRestoreOrDelete extends DialogFragment implements View.OnClickListener {
    private Button btnRestore, btnDelete, btnCancel;
    private ContactDialogRestoreOrDelete.OnDialogClickListener listener;
    private TextView tvNumber;

    public static final int CLICK_RESTORE = 2;
    public static final int CLICK_DELETE = 1;
    public static final int CLICK_CANCEL = 0;
    public static final int CLICK_DISMISS = -1;
    public static final String MESSAGE = "message";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_restore_or_delete, null);
        getDialog().setTitle(getString(R.string.choose));
        btnRestore = (Button) view.findViewById(R.id.btn_dialog_restore_or_delete_restore);
        btnDelete = (Button) view.findViewById(R.id.btn_dialog_restore_or_delete_delete);
        btnCancel = (Button) view.findViewById(R.id.btn_dialog_restore_or_delete_cancel);

        btnRestore.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String number = bundle.getString(MESSAGE);
            if (number != null) {
                tvNumber = (TextView) view.findViewById(R.id.tv_dialog_restore_or_delete_number);
                if (tvNumber != null) {
                    tvNumber.setText(number);
                }

            }
        }

        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (listener != null) {
            listener.onClick(CLICK_DISMISS);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void setOnDialogClickListener(ContactDialogRestoreOrDelete.OnDialogClickListener listener) {
        this.listener = listener;

    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            switch (v.getId()) {
                case R.id.btn_dialog_restore_or_delete_restore:
                    listener.onClick(CLICK_RESTORE);
                    dismiss();
                    break;
                case R.id.btn_dialog_restore_or_delete_delete:
                    listener.onClick(CLICK_DELETE);
                    dismiss();
                    break;
                case R.id.btn_dialog_restore_or_delete_cancel:
                    listener.onClick(CLICK_CANCEL);
                    dismiss();
                    break;
            }
        }
    }

    public interface OnDialogClickListener {
        /*
        *   CLICK_RESTORE, CLICK_DELETE, CLISK_CANCEL
         */
        void onClick(int status);
    }
}
