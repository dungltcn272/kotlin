package com.example.customnavigationdrawer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.customnavigationdrawer.fragment.FavoriteFragment
import com.example.adapterviewpagerdeprecated.fragment.HistoryFragment
import com.example.customnavigationdrawer.fragment.HomeFragment

class MyViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> FavoriteFragment()
            2 -> HistoryFragment()
            else -> HomeFragment()
        }
    }
}
