package com.mad.mad_encounters.usermanagement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mad.mad_encounters.R
import com.mad.mad_encounters.deliverymangement.activity.AllDeliveryOrdersActivity

class DeleteOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_order)
    }

    fun navigateback(view: View) {
        val intent = Intent(this, ViewOrder::class.java)
        startActivity(intent)
    }
}