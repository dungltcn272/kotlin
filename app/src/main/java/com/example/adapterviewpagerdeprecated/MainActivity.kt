package com.example.adapterviewpagerdeprecated

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var mTabLayout : TabLayout
    private lateinit var mViewPager : ViewPager2
    private lateinit var mViewPagerAdapter : MyViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTabLayout =findViewById(R.id.tab_layout)
        mViewPager =findViewById(R.id.view_pager)
        mViewPagerAdapter = MyViewPagerAdapter(this)
        mViewPager.adapter= mViewPagerAdapter
        TabLayoutMediator(mTabLayout, mViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "HOME"
                1 -> tab.text = "FAVORITE"
                2 -> tab.text = "HISTORY"
            }
        }.attach()

    }
}