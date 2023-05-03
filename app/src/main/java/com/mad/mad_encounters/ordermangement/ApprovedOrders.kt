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

class ApprovedOrders : AppCompatActivity() {

    private lateinit var appRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var approvedList: ArrayList<OrderStatus>
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_approved_orders)

        appRecyclerView = findViewById(R.id.rvEmp)
        appRecyclerView.layoutManager = LinearLayoutManager(this)
        appRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        approvedList = arrayListOf<OrderStatus>()
        getApprovedList()

        val backButton = findViewById<ImageView>(R.id.backBtn)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun getApprovedList() {
        appRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE
        dbRef = FirebaseDatabase.getInstance().getReference("ApprovedOrders")
        dbRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        approvedList.clear()
                        if (snapshot.exists()) {
                            for (order in snapshot.children) {
                                val orderStatus = order.getValue(OrderStatus::class.java)
                                approvedList.add(orderStatus!!)
                            }
                            appRecyclerView.adapter = ApprovedOrderAdapter(approvedList)
                            appRecyclerView.visibility = View.VISIBLE
                            tvLoadingData.visibility = View.GONE
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@ApprovedOrders, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        )

    }

    fun deleteOrder(position: Int){
        val order = approvedList[position]
        val dbRef = FirebaseDatabase.getInstance().getReference("ApprovedOrders")
        dbRef.child(order.orderID).removeValue()
        Toast.makeText(this, "Order Deleted", Toast.LENGTH_SHORT).show()
    }
    fun rejectOrder(position: Int){
        val order = approvedList[position]
        order.status = "Rejected"
        val dbRefApp = FirebaseDatabase.getInstance().getReference("ApprovedOrders")
        dbRef.child(order.orderID).removeValue()
        val dbRefRej =  FirebaseDatabase.getInstance().getReference("RejectedOrders")
        dbRefRej.child(order.orderID).setValue(order)
        Toast.makeText(this, "Order Rejected", Toast.LENGTH_SHORT).show()
    }
}