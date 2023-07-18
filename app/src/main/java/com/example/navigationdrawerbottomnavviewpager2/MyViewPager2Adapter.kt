package com.example.navigationdrawerbottomnavviewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.navigationdrawerbottomnavviewpager2.fragment.FavoriteFragment
import com.example.navigationdrawerbottomnavviewpager2.fragment.HistoryFragment
import com.example.navigationdrawerbottomnavviewpager2.fragment.HomeFragment

class MyViewPager2Adapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1-> FavoriteFragment()
            2 -> HistoryFragment()
            else -> HomeFragment()
        }
    }
}