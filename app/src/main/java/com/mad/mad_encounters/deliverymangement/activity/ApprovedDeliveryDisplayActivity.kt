package com.mad.mad_encounters.deliverymangement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mad.mad_encounters.R
import com.mad.mad_encounters.deliverymangement.adapter.ApprovedOrderAdapter
import com.mad.mad_encounters.deliverymangement.model.ApproveModel

class ApprovedDeliveryDisplayActivity : AppCompatActivity() {

    private lateinit var appRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var approvedList: ArrayList<ApproveModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.approved_delivery_display)

        appRecyclerView = findViewById(R.id.rvdelivery)
        appRecyclerView.layoutManager = LinearLayoutManager(this)
        appRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        approvedList = arrayListOf<ApproveModel>()
        getApprovedList()

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
                        val orderStatus = order.getValue(ApproveModel::class.java)
                        approvedList.add(orderStatus!!)
                    }
                    appRecyclerView.adapter = ApprovedOrderAdapter(approvedList)
                    appRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ApprovedDeliveryDisplayActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
        )

    }

    fun navigateback(view: View) {
        val intent = Intent(this, DeliveryDashboardActivity::class.java)
        startActivity(intent)
    }

}
