package com.example.zolotuhinartem.contactlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContactActivity extends AppCompatActivity {
    public static final String LOG_TAG = "MY_TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        if (findViewById(R.id.fl_activity_contact_view_pager) != null){
            ContactViewPagerFragment viewPagerFragment = new ContactViewPagerFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_contact_view_pager, viewPagerFragment,
                    ContactViewPagerFragment.class.getName()).commit();
        }
        if (findViewById(R.id.fl_activity_contact_information) != null){
            ContactInformationFragment contactInformationFragment = new ContactInformationFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_contact_information, contactInformationFragment,
                    ContactViewPagerFragment.class.getName()).commit();
        }
    }
}
