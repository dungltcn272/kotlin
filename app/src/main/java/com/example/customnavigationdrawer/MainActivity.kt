package com.example.customnavigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val FRAGMENT_HOME =1
    private val FRAGMENT_FAVORITE =2
    private val FRAGMENT_HISTORY =3

    private var mCurrentFragment =FRAGMENT_HOME
    private lateinit var mTabLayout : TabLayout

    private  lateinit var mDrawerLayout : DrawerLayout
    private lateinit var mViewPager2: ViewPager2

    private lateinit var myViewPagerAdapter : MyViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDrawerLayout =findViewById(R.id.drawer_layout)
        val mToolbar : Toolbar =findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)

        // ánh xạ
        mTabLayout=findViewById(R.id.tab_layout)
        mViewPager2=findViewById(R.id.view_pager_2)
        myViewPagerAdapter= MyViewPagerAdapter(this)
        mViewPager2.adapter =myViewPagerAdapter

        TabLayoutMediator(mTabLayout, mViewPager2) { tab, position ->
            when(position){
                0 -> tab.setText(R.string.nav_home)
                1 -> tab.setText(R.string.nav_favorite)
                2 -> tab.setText(R.string.nav_history)
            }
        }.attach()

        val toggle = ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView: NavigationView =findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener (this)

//        replaceFragment(HomeFragment())
        // màn hình ban đầu là home
        navigationView.menu.findItem(R.id.nav_home).isChecked = true
        mViewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // Xử lý khi trang được chọn
                super.onPageSelected(position)
                when(position){
                    0 -> {
                        mCurrentFragment=FRAGMENT_HOME
                        navigationView.menu.findItem(R.id.nav_home).isChecked =true
                    }
                    1 -> {
                        mCurrentFragment=FRAGMENT_FAVORITE
                        navigationView.menu.findItem(R.id.nav_favorite).isChecked =true
                    }
                    2 -> {
                        mCurrentFragment=FRAGMENT_HISTORY
                        navigationView.menu.findItem(R.id.nav_history).isChecked =true
                    }
                }
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val id : Int =item.itemId
        if(id==R.id.nav_home){
            if(mCurrentFragment!=FRAGMENT_HOME){
//                replaceFragment(HomeFragment())
                mViewPager2.currentItem = 0
                mCurrentFragment=FRAGMENT_HOME
            }
        }
        else if(id==R.id.nav_favorite){
            if(mCurrentFragment!=FRAGMENT_FAVORITE){
//                replaceFragment(FavoriteFragment())
                mViewPager2.currentItem = 1
                mCurrentFragment=FRAGMENT_FAVORITE
            }
        }
        else if(id==R.id.nav_history){
            if(mCurrentFragment!=FRAGMENT_HISTORY){
//                replaceFragment(HistoryFragment())
                mViewPager2.currentItem = 2
                mCurrentFragment=FRAGMENT_HISTORY
            }
        }
        // khi click vào thì drawer sẽ thu lại
        mDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    @Deprecated("Deprecated in Java")
    // khi ấn nút back thì app sẽ k thoát ra ngoài ma chỉ đóng drawer
    override fun onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }
    }
//    private fun replaceFragment(fragment: Fragment) {
//        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.view_pager_2, fragment)
//        // xác nhận và thực hiện sự thay đổi fragment
//        transaction.commit()
//    }


}