package com.mad.mad_encounters.ordermangement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mad.mad_encounters.R
import com.mad.mad_encounters.ordermangement.adapter.ApprovedOrderAdapter
import com.mad.mad_encounters.ordermangement.model.OrderStatus

class RejectedOrders : AppCompatActivity() {
    private lateinit var rejRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var rejectedList: ArrayList<OrderStatus>
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rejected_orders)

        rejRecyclerView = findViewById(R.id.rvEmp)
        rejRecyclerView.layoutManager = LinearLayoutManager(this)
        rejRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        rejectedList = arrayListOf<OrderStatus>()
        getRejectedList()

        val backButton = findViewById<ImageView>(R.id.backBtn)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun getRejectedList() {
        rejRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE
        dbRef = FirebaseDatabase.getInstance().getReference("RejectedOrders")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                rejectedList.clear()
                if (snapshot.exists()) {
                    for (order in snapshot.children) {
                        val orderStatus = order.getValue(OrderStatus::class.java)
                        rejectedList.add(orderStatus!!)
                    }
                    rejRecyclerView.adapter = ApprovedOrderAdapter(rejectedList)
                    rejRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@RejectedOrders, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
        )

    }
}