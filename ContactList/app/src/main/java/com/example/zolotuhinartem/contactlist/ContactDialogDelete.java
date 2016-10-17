package com.example.zolotuhinartem.contactlist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by zolotuhinartem on 17.10.16.
 */

public class ContactDialogDelete extends DialogFragment implements View.OnClickListener {

    private Button btnYes, btnNo;
    private OnDialogClickListener listener;

    public static final int CLICK_YES = 1;
    public static final int CLICK_NO = 0;
    public static final int CLICK_DISMISS = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_delete, null);
        btnYes = (Button) view.findViewById(R.id.btn_dialog_delete_yes);
        btnNo = (Button) view.findViewById(R.id.btn_dialog_delete_no);

        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (listener != null){
            listener.onClick(CLICK_DISMISS);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    public void setOnDialogClickListener(OnDialogClickListener listener){
        this.listener = listener;

    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            switch (v.getId()){
                case R.id.btn_dialog_delete_yes:
                    listener.onClick(CLICK_YES);
                    dismiss();
                    break;
                case R.id.btn_dialog_delete_no:
                    listener.onClick(CLICK_NO);
                    dismiss();
            }
        }
    }

    public interface OnDialogClickListener{
        /*
        *   CLICK_YES, CLICK_NO, CLISK_DISMISS
         */
        void onClick(int status);
    }

}
