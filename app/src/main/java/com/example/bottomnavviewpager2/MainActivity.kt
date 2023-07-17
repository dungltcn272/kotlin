package com.example.bottomnavviewpager2

import DepthPageTransformer
import ZoomOutPageTransformer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var mViewPager2: ViewPager2
    private lateinit var mBottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ánh xạ view
        mViewPager2=findViewById(R.id.view_pager_2)
        mBottomNavigationView=findViewById(R.id.bottom_navigation)

        val myViewPagerAdapter = MyViewPagerAdapter(this)
        mViewPager2.adapter =myViewPagerAdapter

        mBottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    mViewPager2.currentItem = 0
                }
                R.id.bottom_favorite -> {
                    mViewPager2.currentItem = 1
                }
                R.id.bottom_history -> {
                    mViewPager2.currentItem = 2
                }
            }
            true
        }
        mViewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0 -> mBottomNavigationView.menu.findItem(R.id.bottom_home).isChecked = true
                    1 -> mBottomNavigationView.menu.findItem(R.id.bottom_favorite).isChecked = true
                    2 -> mBottomNavigationView.menu.findItem(R.id.bottom_history).isChecked = true
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_zoom -> mViewPager2.setPageTransformer(ZoomOutPageTransformer())
            R.id.menu_depth -> mViewPager2.setPageTransformer(DepthPageTransformer())
        }
        return true
    }
}