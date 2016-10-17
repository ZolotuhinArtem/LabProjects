package com.example.zolotuhinartem.contactlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zolotuhinartem on 15.10.16.
 */

public class ContactInformationFragment extends Fragment {
    private TextView tvNumber;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        tvNumber = (TextView) view.findViewById(R.id.tv_fragment_information_number);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            String number = bundle.getString(Contact.KEY_NUMBER, "");
            tvNumber.setText(number);
        }
    }
}
