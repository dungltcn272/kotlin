package com.example.databindingtutorial.observable_databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.databindingtutorial.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {

    private lateinit var mActivity2Binding: Activity2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivity2Binding= Activity2Binding.inflate(layoutInflater)
        val activity2ViewModel = Activity2ViewModel("tien dung")
        mActivity2Binding.activity2ViewModel=activity2ViewModel

        mActivity2Binding.editTextMessage.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                Log.e("dung", s.toString())
                Log.e("dung2", activity2ViewModel.message)
            }
        })

        setContentView(mActivity2Binding.root)
    }
}