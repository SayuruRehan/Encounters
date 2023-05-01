package com.mad.mad_encounters.ordermangement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.mad.mad_encounters.R

class OrderManagementMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_management_main)

        val approveCount = findViewById<TextView>(R.id.approve_count)
        approveCount.setOnClickListener{
            val intent = Intent(this, ApprovedOrders::class.java)
            startActivity(intent)
        }
        val rejectCount = findViewById<TextView>(R.id.reject_count)
        rejectCount.setOnClickListener{
            val intent = Intent(this, RejectedOrders::class.java)
            startActivity(intent)
        }

        val approveOrder = findViewById<Button>(R.id.approveBtn)
        approveOrder.setOnClickListener{
            val intent = Intent(this, ApproveForm::class.java)
            startActivity(intent)
        }
        val rejectOrder = findViewById<Button>(R.id.rejectBtn)
        rejectOrder.setOnClickListener{
            val intent = Intent(this, RejectForm::class.java)
            startActivity(intent)
        }

    }
}