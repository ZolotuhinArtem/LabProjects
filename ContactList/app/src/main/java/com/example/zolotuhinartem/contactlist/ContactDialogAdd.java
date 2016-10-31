package com.example.zolotuhinartem.contactlist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by zolotuhinartem on 18.10.16.
 */

public class ContactDialogAdd extends DialogFragment implements View.OnClickListener {

    public static final int CLICK_ADD = 1;
    public static final int CLICK_CANCEL = 0;
    public static final int CLICK_DISMISS = -1;

    private Button btnAdd, btnCancel;
    private EditText etNumber;

    private ContactDialogAdd.OnDialogClickListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add, null);

        btnAdd = (Button) view.findViewById(R.id.btn_dialog_add_add);
        btnCancel = (Button) view.findViewById(R.id.btn_dialog_add_cancel);

        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        etNumber = (EditText) view.findViewById(R.id.et_dialog_add_number);

        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (listener != null) {
            listener.onClick(CLICK_DISMISS, null);
        }
    }

    public void setListener(OnDialogClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            switch (v.getId()) {
                case R.id.btn_dialog_add_add:
                    listener.onClick(CLICK_ADD, etNumber.getText().toString());
                    dismiss();
                    break;
                case R.id.btn_dialog_add_cancel:
                    listener.onClick(CLICK_CANCEL, etNumber.getText().toString());
                    dismiss();
                    break;
                default:
                    dismiss();
                    break;
            }
        }
    }

    public interface OnDialogClickListener {
        void onClick(int status, String number);
    }
}
