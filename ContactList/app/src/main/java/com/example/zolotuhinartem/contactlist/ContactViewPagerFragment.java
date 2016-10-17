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
            ContactFragmentPagerAdapter adapter = new ContactFragmentPagerAdapter(getActivity().getSupportFragmentManager(), 2);
            viewPager.setAdapter(adapter);
        }

    }
}
