package com.example.zolotuhinartem.contactlist;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by zolotuhinartem on 15.10.16.
 */

public class ContactFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private FragmentManager fragmentManager;
    private int countOfFragments;
    private ContactFragment contactFragment;
    private ContactDeletedFragment contactDeletedFragment;
    private Context context;

    public ContactFragmentPagerAdapter(FragmentManager fragmentManager, int countOfFragments, Context context) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.countOfFragments = countOfFragments;
        this.context = context;
    }

    @Override
    public int getCount() {
        return countOfFragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (context != null) {
            switch (position) {
                case 0:
                    return context.getString(R.string.contacts);
                case 1:
                    return context.getString(R.string.deleted_contacts);
                default:
                    return context.getString(R.string.contacts);
            }
        } else {
            return "";
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                this.contactFragment = new ContactFragment();
                return this.contactFragment;
            case 1:
                this.contactDeletedFragment = new ContactDeletedFragment();
                return this.contactDeletedFragment;
            default:
                this.contactFragment = new ContactFragment();
                return this.contactFragment;
        }
    }

    public ContactFragment getContactFragment() {
        return contactFragment;
    }

    public ContactDeletedFragment getContactDeletedFragment() {
        return contactDeletedFragment;
    }
}
