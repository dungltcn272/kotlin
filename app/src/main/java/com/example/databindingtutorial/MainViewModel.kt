package com.example.databindingtutorial

import android.util.Log
import android.view.View

class MainViewModel(private var name: String) {

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }
    fun showLog() {
        Log.e("tien dung", "dung")
    }
    fun showLog2(message : String) {
        Log.e("tien dung", message)
    }
    fun showLog3(view: View, message : String) {
        Log.e("tien dung", message)
    }
    fun showLog4(userViewModel: UserViewModel) {
        Log.e("tien dung", userViewModel.getName()+", " + userViewModel.getAddress())
    }
}
