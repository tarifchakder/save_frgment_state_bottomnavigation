/*
 * Created by Tarif on 2/7/21 3:10 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 2/7/21 3:10 PM
 */

package com.tarif.bottomnav_fragment_state.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class CustomViewPager(fm : FragmentManager,lifeCycle : Lifecycle) : FragmentStateAdapter(fm,lifeCycle){

    private var fragmentList : MutableList<Fragment> = ArrayList()

    fun addFragment(fragment : Fragment){
        fragmentList.add(fragment)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}