package com.mad.mad_encounters.ordermangement.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mad.mad_encounters.R
import com.mad.mad_encounters.deliverymangement.activity.DeliveryDashboardActivity

class OrderManagementMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_management_main)

        val menuIcon = findViewById<ImageView>(R.id.menu)
        menuIcon.setOnClickListener {
            val intent = Intent(this, DeliveryDashboardActivity::class.java)
            startActivity(intent)
        }
        val dbRefApproved = FirebaseDatabase.getInstance().getReference("ApprovedOrders")
        dbRefApproved.keepSynced(true)

        val dbRefRejected = FirebaseDatabase.getInstance().getReference("RejectedOrders")

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
        dbRefApproved.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Get the number of children (i.e. the number of approved orders)
                val count = snapshot.childrenCount.toInt()

                // Set the count as the text of the approved_count TextView
                approveCount.text = count.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Error: $error", Toast.LENGTH_SHORT).show()
            }

        })
        dbRefRejected.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Get the number of children (i.e. the number of approved orders)
                val count = snapshot.childrenCount.toInt()

                // Set the count as the text of the approved_count TextView
                rejectCount.text = count.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}