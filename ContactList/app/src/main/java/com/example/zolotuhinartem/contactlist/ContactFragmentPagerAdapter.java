package com.example.zolotuhinartem.contactlist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by zolotuhinartem on 15.10.16.
 */

public class ContactFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private FragmentManager fragmentManager;
    private int countOfFragments;
    private ContactFragment contactFragment;
    private ContactDeletedFragment contactDeletedFragment;

    public ContactFragmentPagerAdapter(FragmentManager fragmentManager, int countOfFragments) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.countOfFragments = countOfFragments;
    }

    @Override
    public int getCount() {
        return countOfFragments;
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
