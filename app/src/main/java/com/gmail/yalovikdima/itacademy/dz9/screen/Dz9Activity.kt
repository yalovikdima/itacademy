package com.gmail.yalovikdima.itacademy.dz9.screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gmail.yalovikdima.itacademy.R

class Dz9Activity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun getIntent(context: Context) = Intent(context, Dz9Activity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9)
    }

}