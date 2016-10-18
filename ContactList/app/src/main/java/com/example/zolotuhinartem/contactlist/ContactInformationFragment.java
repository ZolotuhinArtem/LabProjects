package com.example.zolotuhinartem.contactlist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by zolotuhinartem on 15.10.16.
 */

public class ContactInformationFragment extends Fragment implements View.OnClickListener {
    private TextView tvNumber;
    private Button btnCall, btnSendSMS;
    private EditText etSMS;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        tvNumber = (TextView) view.findViewById(R.id.tv_fragment_information_number);
        btnCall = (Button) view.findViewById(R.id.btn_fragment_information_call);
        btnSendSMS = (Button) view.findViewById(R.id.btn_fragment_information_send_sms);
        btnCall.setOnClickListener(this);
        btnSendSMS.setOnClickListener(this);
        etSMS = (EditText) view.findViewById(R.id.et_fragment_information_sms);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String number = bundle.getString(Contact.KEY_NUMBER, "");
            tvNumber.setText(number);
        }
    }

    @Override
    public void onClick(View v) {
        String number = tvNumber.getText().toString();
        if (ValidDataChecker.checkNumber(number)) {
            Intent intent;
            switch (v.getId()) {
                case R.id.btn_fragment_information_call:
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                    startActivity(intent);
                    break;
                case R.id.btn_fragment_information_send_sms:
                    intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + number));
                    intent.putExtra("sms_body", etSMS.getText().toString());
                    startActivity(intent);
                    break;
            }
        }
    }
}
