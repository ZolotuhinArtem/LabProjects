package com.example.zolotuhinartem.contactlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity implements OnContactClickListener {
    private boolean isLandscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        isLandscape = false;
        if (findViewById(R.id.fl_activity_contact_view_pager) != null) {
            ContactViewPagerFragment viewPagerFragment = new ContactViewPagerFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_contact_view_pager, viewPagerFragment,
                    ContactViewPagerFragment.class.getName()).commit();
        }
        if (findViewById(R.id.fl_activity_contact_information) != null) {
            isLandscape = true;
            ContactInformationFragment contactInformationFragment = new ContactInformationFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_contact_information, contactInformationFragment,
                    ContactInformationFragment.class.getName()).commit();
        }

    }

    @Override
    public void onContactClick(Contact contact) {
        if (isLandscape) {
            ContactInformationFragment fragment = (ContactInformationFragment) getSupportFragmentManager().findFragmentByTag(ContactInformationFragment.class.getName());
            TextView tvNumber = (TextView) fragment.getView().findViewById(R.id.tv_fragment_information_number);
            tvNumber.setText(contact.getNumber());
        } else {
            Intent intent = new Intent(ContactActivity.this, InformationActivity.class);
            contact.fillIntent(intent);
            startActivity(intent);
        }
    }

    @Override
    public void onDeletedContactClick(Contact contact) {

    }
}
