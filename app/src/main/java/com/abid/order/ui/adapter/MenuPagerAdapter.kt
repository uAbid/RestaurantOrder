package com.abid.order.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.abid.order.ui.model.Menu

class MenuPagerAdapter(var menus: ArrayList<Menu>, fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return menus[position].fragment
    }


    override fun getCount(): Int {
        return menus.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return menus[position].title
    }
}