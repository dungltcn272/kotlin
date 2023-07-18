package com.example.menuright

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.menuright.fragment.FavoriteFragment
import com.example.menuright.fragment.HistoryFragment
import com.example.menuright.fragment.HomeFragment
import com.google.android.material.navigation.NavigationView


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val FRAGMENT_HOME=0
    private val FRAGMENT_FAVORITE=1
    private val FRAGMENT_HISTORY=2

    private var mCurrenFragment =FRAGMENT_HOME

    private lateinit var mDrawerLayout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDrawerLayout = findViewById(R.id.drawer_layout)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navigationView: NavigationView =findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(this)

        // start app
        replaceFragment(HomeFragment())
        navigationView.menu.findItem(R.id.nav_home).isChecked=true

    }

    // tạo menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // khi ấn vào icon menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_toolbar){
            mDrawerLayout.openDrawer(GravityCompat.END)
        }
        return super.onOptionsItemSelected(item)
    }

    // khi ấn chọn các selection
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> {
                if(mCurrenFragment!=FRAGMENT_HOME) {
                    replaceFragment(HomeFragment())
                    mCurrenFragment=FRAGMENT_HOME
                }
            }
            R.id.nav_favorite -> {
                if(mCurrenFragment!=FRAGMENT_FAVORITE) {
                    replaceFragment(FavoriteFragment())
                    mCurrenFragment=FRAGMENT_FAVORITE
                }
            }
            R.id.nav_history -> {
                if(mCurrenFragment!=FRAGMENT_HOME){
                    replaceFragment(HistoryFragment())
                    mCurrenFragment=FRAGMENT_HISTORY
                }
            }
        }
        // đóng drawer khi chọn
        mDrawerLayout.closeDrawer(GravityCompat.END)
        return true
    }
    // di chuyển fragment
    private fun replaceFragment(fragment: Fragment){
        val transaction : FragmentTransaction =supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, fragment)
        transaction.commit()
    }
    // xử lí khi ấn nút back
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.END)){
            mDrawerLayout.closeDrawer(GravityCompat.END)
        }
        else super.onBackPressed()
    }
}