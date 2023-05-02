package com.mad.mad_encounters.deliverymangement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mad.mad_encounters.R

class DeleteDeliveryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_delivery)
    }

    fun navigateback(view: View) {
        val intent = Intent(this, AllDeliveryOrdersActivity::class.java)
        startActivity(intent)
    }
}