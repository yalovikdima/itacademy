package com.gmail.yalovikdima.itacademy.dz9.screen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.yalovikdima.itacademy.R

class ListFragment: Fragment(){

    companion object {
        fun getInstance()= ListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("AAA", "onCreateView")
        return inflater.inflate(R.layout.activity_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("AAA", "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

}