package com.example.databindingtutorial

class MyFragmentViewModel(private var title : String) {
    fun getTilte(): String {
        return this.title
    }
    fun setTitle(title:String){
        this.title=title
    }
}