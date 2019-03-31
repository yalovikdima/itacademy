package com.gmail.yalovikdima.itacademy.dz9.screen

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {

    private val FRAGMENT = "fragment"
    private val ADD = "add"

    companion object {
        fun getInstance() = SecondActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (resources.configuration.orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
            return
        }
        val fragment = intent.getStringExtra(FRAGMENT)
        if (fragment != null && fragment == ADD) {
            if (savedInstanceState == null) {
                val details = AddItemFragment()
                supportFragmentManager.beginTransaction().add(android.R.id.content, details).commit()
            }
        } else {
            if (savedInstanceState == null) {
                val details = DetailsFragment()
                details.arguments = intent.extras
                supportFragmentManager.beginTransaction().add(android.R.id.content, details).commit()
            }
        }
    }

}