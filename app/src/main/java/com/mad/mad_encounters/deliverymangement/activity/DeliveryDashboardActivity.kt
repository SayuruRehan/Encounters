package com.mad.mad_encounters.deliverymangement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mad.mad_encounters.R

class DeliveryDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delivery_dashboard)

    }
    fun navigateToAdd(view: View) {
        val intent = Intent(this, AddDeliveryActivity::class.java)
        startActivity(intent)
    }

    fun navigateToAll(view: View) {
        val intent = Intent(this, AllDeliveryOrdersActivity::class.java)
        startActivity(intent)
    }

    fun navigateToApproved(view: View) {
        val intent = Intent(this, ApprovedDeliveryDisplayActivity::class.java)
        startActivity(intent)
    }
}
