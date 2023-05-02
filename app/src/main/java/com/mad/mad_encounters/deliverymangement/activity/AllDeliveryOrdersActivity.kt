package com.mad.mad_encounters.deliverymangement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mad.mad_encounters.R
import com.mad.mad_encounters.deliverymangement.adapter.DeliveryAdapter
import com.mad.mad_encounters.deliverymangement.model.DeliveryModel
import kotlin.jvm.internal.Ref

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

//        btnUpdate.setOnClickListener{
//            openUpdateDialog(
//                intent.getStringExtra("delivertID").toString(),
//                intent.getStringExtra("proName").toString(),
////                intent.getStringExtra("address").toString()
//            )
//        }
    }

//    private fun openUpdateDialog(delivertID: String,proName: String){
//        val dDialog = AlertDialog.Builder(this)
//        val inflater = layoutInflater
//        val dDialogView = inflater.inflate(R.layout.update_delivery,null)
//
//        dDialog.setView(dDialogView)
//
//        val proName = dDialogView.findViewById<EditText>(R.id.tv_update_product_name)
//        val address = dDialogView.findViewById<EditText>(R.id.tv_update_address)
//        val date = dDialogView.findViewById<EditText>(R.id.tv_update_delivery_date)
//        val btnUpdateData = dDialogView.findViewById<Button>(R.id.btn_update)
//
//        proName.setText(intent.getStringExtra("proName").toString())
//        address.setText(intent.getStringExtra("address").toString())
//        date.setText(intent.getStringExtra("date").toString())
//
//        dDialog.setTitle("Updating $proName Record")
//
//        val alertDialog = dDialog.create()
//        alertDialog.show()
//
//        btnUpdateData.setOnClickListener{
//            updateDeliveryData(
//                delivertID,
//                proName.text.toString(),
//                address.text.toString(),
//                date.text.toString()
//            )
//            Toast.makeText(applicationContext, "Delivery data Updated", Toast.LENGTH_LONG).show()
//
//            tvDeliveryname.text = proName.text.toString()
//            tvDeliveryAddress.text = proName.text.toString()
//            tvDeliveryDate.text = proName.text.toString()
//
//            alertDialog.dismiss()
//        }
//    }

//    private fun updateDeliveryData(id:String, name:String, address:String, date:String){
//        val dbRef = FirebaseDatabase.getInstance().getReference("Deliveries")
//        val deliveryInfo = DeliveryModel(id, name, address, date)
//        dbRef.setValue((deliveryInfo))
//    }

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


