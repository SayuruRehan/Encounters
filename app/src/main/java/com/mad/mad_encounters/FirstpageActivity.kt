package com.mad.mad_encounters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mad.mad_encounters.ordermangement.activities.OrderManagementMain
import com.mad.mad_encounters.usermanagement.activity.Home

class FirstpageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.firstpage)
    }
    fun navigateuserpage(view: View) {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }

    fun navigateadminpage(view: View) {
        val intent = Intent(this, OrderManagementMain::class.java)
        startActivity(intent)
    }
}