package com.mad.mad_encounters.usermanagement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mad.mad_encounters.R
import com.mad.mad_encounters.usermanagement.adapter.OrderAdapter
import com.mad.mad_encounters.usermanagement.model.OrderModel

class ViewOrder : AppCompatActivity() {

    private lateinit var orderRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var orderList: ArrayList<OrderModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_order)

        orderRecyclerView = findViewById(R.id.rvOrder)
        orderRecyclerView.layoutManager = LinearLayoutManager(this)
        orderRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        orderList = arrayListOf<OrderModel>()

        getOrderData()
    }

    private fun getOrderData() {
        orderRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Orders")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                orderList.clear()
                if (snapshot.exists()) {
                    for (orderSnap in snapshot. children) {
                        val orderData = orderSnap.getValue(OrderModel::class.java)
                        orderList.add(orderData!!)
                    }
                    val oAdapter = OrderAdapter(orderList)
                    orderRecyclerView.adapter = oAdapter

                    oAdapter.setOnItemClickListener(object : OrderAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@ViewOrder, ViewOrder::class.java)

                            intent.putExtra("orderID", orderList[position].orderID)
                            intent.putExtra("proName", orderList[position].ProductName)
                            intent.putExtra("destAddress", orderList[position].DestAddress)
                            startActivity(intent)
                        }
                    })

                    orderRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun navigateback(view: View) {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }

    fun navigateupdate(view: View) {
        val intent = Intent(this, UpdateOrder::class.java)
        startActivity(intent)
    }

    fun navigatedelete(view: View) {
        val intent = Intent(this, DeleteOrder::class.java)
        startActivity(intent)
    }
}