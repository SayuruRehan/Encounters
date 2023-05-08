package com.mad.mad_encounters.usermanagement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mad.mad_encounters.R

class UserProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
    }

    fun navigateback(view: View) {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }
}