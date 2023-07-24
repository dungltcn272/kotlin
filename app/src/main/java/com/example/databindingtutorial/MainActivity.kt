package com.example.databindingtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingtutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mActivityMainBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val mMainViewModel = MainViewModel("This is a message 3")
        mActivityMainBinding.mainViewModel=mMainViewModel
        mActivityMainBinding.userViewModel=UserViewModel("dung", "Ha noi")
        setContentView(mActivityMainBinding.root)
        displayListUser()
    }

    private fun displayListUser() {
        val rcvUser : RecyclerView = mActivityMainBinding.rcvUser
        rcvUser.layoutManager =LinearLayoutManager(this)

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rcvUser.addItemDecoration(itemDecoration)

        val userAdapter= UserAdapter(getListUser())
        rcvUser.adapter=userAdapter
    }

    private fun getListUser(): List<UserViewModel> {
        val list :MutableList<UserViewModel> = ArrayList()
        list.add(UserViewModel("User 1", "hoan kiem Ha Noi"))
        list.add(UserViewModel("User 2", "hoan kiem Ha Noi"))
        list.add(UserViewModel("User 3", "hoan kiem Ha Noi"))
        list.add(UserViewModel("User 4", "hoan kiem Ha Noi"))
        list.add(UserViewModel("User 5", "hoan kiem Ha Noi"))
        list.add(UserViewModel("User 6", "hoan kiem Ha Noi"))
        list.add(UserViewModel("User 7", "hoan kiem Ha Noi"))
        return list
    }

}