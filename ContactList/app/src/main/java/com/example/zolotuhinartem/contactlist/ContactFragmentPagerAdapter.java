package com.example.zolotuhinartem.contactlist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by zolotuhinartem on 15.10.16.
 */

public class ContactFragmentPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager fragmentManager;
    private int countOfFragments;

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
                return new ContactFragment();
            case 1:
                return new ContactDeletedFragment();
            default:
                return new ContactFragment();
        }
    }

}
