package com.example.zolotuhinartem.contactlist;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        if(findViewById(R.id.fl_activity_information_information) != null){
            ContactInformationFragment fragment = new ContactInformationFragment();
            Intent intent = getIntent();
            Contact contact = Contact.getFromIntent(intent);
            Bundle bundle = new Bundle();
            bundle.putString(Contact.KEY_NUMBER, contact.getNumber());
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_information_information, fragment).commit();
        }
    }
}
