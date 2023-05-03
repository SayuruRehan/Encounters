package com.mad.mad_encounters.deliverymangement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mad.mad_encounters.R
import com.mad.mad_encounters.deliverymangement.adapter.DeliveryAdapter
import com.mad.mad_encounters.deliverymangement.model.DeliveryModel

class AllDeliveryOrdersActivity : AppCompatActivity() {

    private lateinit var deliveryRecyclerView: RecyclerView
    private lateinit var tvLodingData: TextView
    private lateinit var deliveryList: ArrayList<DeliveryModel>
    private lateinit var dbRef: DatabaseReference

//    private lateinit var tvDeliveryname: TextView
//    private lateinit var tvDeliveryAddress: TextView
//    private lateinit var tvDeliveryDate: TextView
//    private lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_delivery_orders)

        deliveryRecyclerView = findViewById(R.id.rvdelivery)
        deliveryRecyclerView.layoutManager = LinearLayoutManager(this)
        deliveryRecyclerView.setHasFixedSize(true)
        tvLodingData = findViewById(R.id.tvLoadingData)

        deliveryList = arrayListOf<DeliveryModel>()

        getDeliveryData()

    }

    private fun getDeliveryData() {
        deliveryRecyclerView.visibility = View.GONE
        tvLodingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Deliveries")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                deliveryList.clear()
                if (snapshot.exists()){
                    for (deliverySnap in snapshot. children){
                        val deliveryData = deliverySnap.getValue(DeliveryModel::class.java)
                        deliveryList.add(deliveryData!!)
                    }
                    val dAdapter = DeliveryAdapter(deliveryList)
                    deliveryRecyclerView.adapter = dAdapter
                    
                    dAdapter.setOnItemClickListener(object : DeliveryAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@AllDeliveryOrdersActivity, DisplayDeliveryActivity::class.java)

                            intent.putExtra("delivertID", deliveryList[position].delivertID)
                            intent.putExtra("proName", deliveryList[position].ProName)
                            intent.putExtra("address", deliveryList[position].Address)
                            intent.putExtra("date", deliveryList[position].Date)
                            startActivity(intent)
                        }

                    })

                    deliveryRecyclerView.visibility = View.VISIBLE
                    tvLodingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun navigateback(view: View) {
        val intent = Intent(this, DeliveryDashboardActivity::class.java)
        startActivity(intent)
    }

    fun navigateupdate(view: View) {
        val intent = Intent(this, UpdateDeliveryActivity::class.java)
        startActivity(intent)
    }

    fun navigatedelete(view: View) {
        val intent = Intent(this, DeleteDeliveryActivity::class.java)
        startActivity(intent)
    }
}


