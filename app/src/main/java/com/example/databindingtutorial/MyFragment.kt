package com.example.databindingtutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.databindingtutorial.databinding.FragmentMyBinding

class MyFragment :Fragment(){
    private lateinit var mFragmentMyBinding : FragmentMyBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mFragmentMyBinding = FragmentMyBinding.inflate(inflater, container, false)
        val mMyFragmentViewModel= MyFragmentViewModel("data binding")
        mFragmentMyBinding.myFragmentViewModel=mMyFragmentViewModel

        return mFragmentMyBinding.root
    }
}