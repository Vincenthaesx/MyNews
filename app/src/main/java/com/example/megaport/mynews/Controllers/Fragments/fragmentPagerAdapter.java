package com.example.megaport.mynews.Controllers.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

// Class used to create the different tabs displayed on the action bar

public class fragmentPagerAdapter extends FragmentPagerAdapter {
    private final String[] tabTitles = new String[] { "TOP STORIES", "MOST POPULAR", "POLITICS"};

    public fragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() { return 3; }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    // Generate title based on item position
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}