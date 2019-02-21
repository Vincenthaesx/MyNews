package com.example.megaport.mynews.Controllers.Fragments

import androidx.fragment.app.FragmentManager

// Class used to create the different tabs displayed on the action bar

class FragmentPagerAdapter(fm: FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {
    private val tabTitles = arrayOf("TOP STORIES", "MOST POPULAR", "POLITICS")
    private val listFragment = listOf(MainFragment.newInstance(0),
            MainFragment.newInstance(1),
            MainFragment.newInstance(2))

    override fun getCount(): Int {
        return listFragment.size
    }

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return listFragment[position]
    }

    // Generate title based on item position
    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}