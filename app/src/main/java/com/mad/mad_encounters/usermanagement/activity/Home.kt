package com.mad.mad_encounters.usermanagement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mad.mad_encounters.R

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun navigateToAdd(view: View) {
        val intent = Intent(this, AddOrder::class.java)
        startActivity(intent)
    }

    fun navigateToAll(view: View) {
        val intent = Intent(this, ViewOrder::class.java)
        startActivity(intent)
    }
}