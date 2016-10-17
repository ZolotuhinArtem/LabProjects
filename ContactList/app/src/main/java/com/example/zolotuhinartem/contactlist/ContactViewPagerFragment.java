package com.example.zolotuhinartem.contactlist;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zolotuhinartem on 15.10.16.
 */

public class ContactViewPagerFragment extends Fragment {

    private ViewPager viewPager;
    private ContactFragmentPagerAdapter contactFragmentPagerAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.vp);
        Activity activity = getActivity();
        if (activity != null) {
            contactFragmentPagerAdapter = new ContactFragmentPagerAdapter(getActivity()
                    .getSupportFragmentManager(), 2);

            viewPager.setAdapter(contactFragmentPagerAdapter);
        }

    }
    public ContactFragment getContactFragment(){
        if (contactFragmentPagerAdapter != null){
            return contactFragmentPagerAdapter.getContactFragment();
        }
        else{
            return null;
        }
    }
    public ContactDeletedFragment getContactDeletedFragment(){
        if (contactFragmentPagerAdapter != null){
            return contactFragmentPagerAdapter.getContactDeletedFragment();
        }
        else{
            return null;
        }
    }
}
