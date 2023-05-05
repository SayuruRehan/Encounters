package com.mad.mad_encounters.ordermangement.activities

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
import com.mad.mad_encounters.ordermangement.adapter.RejectedOrderAdapter
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
                    rejRecyclerView.adapter = RejectedOrderAdapter(rejectedList)
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
    fun deleteOrder(position: Int){
        val order = rejectedList[position]
        val dbRef = FirebaseDatabase.getInstance().getReference("RejectedOrders")
        dbRef.child(order.orderID).removeValue()
        Toast.makeText(this, "Order Deleted", Toast.LENGTH_SHORT).show()
    }
    fun approveOrder(position: Int){
        val order = rejectedList[position]
        order.status = "Approved"
        val dbRef = FirebaseDatabase.getInstance().getReference("RejectedOrders")
        dbRef.child(order.orderID).removeValue()
        val dbRef2 = FirebaseDatabase.getInstance().getReference("ApprovedOrders")
        dbRef2.child(order.orderID).setValue(order)
        Toast.makeText(this, "Order Approved", Toast.LENGTH_SHORT).show()
    }
}