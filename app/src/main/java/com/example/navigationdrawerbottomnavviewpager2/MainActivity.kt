package com.example.navigationdrawerbottomnavviewpager2


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val FRAGMENT_HOME =1
    private val FRAGMENT_FAVORITE =2
    private val FRAGMENT_HISTORY =3

    private var mCurrentFragment =FRAGMENT_HOME

    private lateinit var mViewPager2: ViewPager2
    private lateinit var mBottomNavigationView: BottomNavigationView
    private  lateinit var mDrawerLayout : DrawerLayout
    private lateinit var mMyViewPager2Adapter: MyViewPager2Adapter
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ánh xạ
        mBottomNavigationView=findViewById(R.id.bottom_navigation)
        mDrawerLayout =findViewById(R.id.drawer_layout)
        mViewPager2 =findViewById(R.id.view_pager_2)
        mMyViewPager2Adapter =MyViewPager2Adapter(this)
        mViewPager2.adapter = mMyViewPager2Adapter


        val mToolbar : Toolbar =findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)
        val toggle = ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView: NavigationView =findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener (this)


        // mở app -> trỏ đến home
//        replaceFragment(HomeFragment())
        navigationView.menu.findItem(R.id.nav_home).isChecked = true
        mBottomNavigationView.menu.findItem(R.id.bottom_home).isChecked = true

        mBottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home -> {
//                    navigationView.menu.findItem(R.id.nav_home).isChecked = true
                    openHomeFragment()
                }
                R.id.bottom_favorite -> {
//                    navigationView.menu.findItem(R.id.nav_favorite).isChecked = true
                    openFavoriteFragment()
                }
                R.id.bottom_history -> {
//                    navigationView.menu.findItem(R.id.nav_history).isChecked = true
                    openHistoryFragment()
                }
                else -> {
//                    navigationView.menu.findItem(R.id.nav_home).isChecked = true
                    openHomeFragment()
                }
            }
        }

        // đồng bộ các item của nav drawer và bottom nav
        mViewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0 -> {
                        mCurrentFragment=FRAGMENT_HOME
                        navigationView.menu.findItem(R.id.nav_home).isChecked=true
                        mBottomNavigationView.menu.findItem(R.id.bottom_home).isChecked=true
                    }
                    1 -> {
                        mCurrentFragment=FRAGMENT_FAVORITE
                        navigationView.menu.findItem(R.id.nav_favorite).isChecked=true
                        mBottomNavigationView.menu.findItem(R.id.bottom_favorite).isChecked=true
                    }
                    2 -> {
                        mCurrentFragment=FRAGMENT_HISTORY
                        navigationView.menu.findItem(R.id.nav_history).isChecked=true
                        mBottomNavigationView.menu.findItem(R.id.bottom_history).isChecked=true
                    }
                }
            }
        })

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                openHomeFragment()
//                mBottomNavigationView.menu.findItem(R.id.bottom_home).isChecked = true
            }
            R.id.nav_favorite -> {
                openFavoriteFragment()
//                mBottomNavigationView.menu.findItem(R.id.bottom_favorite).isChecked = true
            }
            R.id.nav_history -> {
                openHistoryFragment()
//                mBottomNavigationView.menu.findItem(R.id.bottom_history).isChecked = true
            }
        }
        // khi click vào thì drawer sẽ thu lại
        mDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // dung chung 3 ham open cac fragment
    private fun openHomeFragment(): Boolean {
        if(mCurrentFragment!=FRAGMENT_HOME){
//            replaceFragment(HomeFragment())
            mViewPager2.currentItem = 0
            mCurrentFragment=FRAGMENT_HOME
        }
        return true
    }

    private fun openFavoriteFragment(): Boolean {
        if(mCurrentFragment!=FRAGMENT_FAVORITE){
//            replaceFragment(FavoriteFragment())
            mViewPager2.currentItem = 1
            mCurrentFragment=FRAGMENT_FAVORITE
        }
        return true
    }

    private fun openHistoryFragment(): Boolean {
        if(mCurrentFragment!=FRAGMENT_HISTORY){
//            replaceFragment(HistoryFragment())
            mViewPager2.currentItem = 2
            mCurrentFragment=FRAGMENT_HISTORY
        }
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
//        transaction.replace(R.id.content_frame, fragment)
//        // xác nhận và thực hiện sự thay đổi fragment
//        transaction.commit()
//    }

}