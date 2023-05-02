package com.mad.mad_encounters.deliverymangement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mad.mad_encounters.R
import com.mad.mad_encounters.deliverymangement.model.DeliveryModel

class AddDeliveryActivity : AppCompatActivity() {

    private lateinit var productName: EditText
    private lateinit var address: EditText
    private lateinit var date: EditText
    private lateinit var btnAdd: Button

    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_delivery)

        productName = findViewById(R.id.tv_product_name)
        address = findViewById(R.id.tv_address)
        date = findViewById(R.id.tv_delivery_date)
        btnAdd = findViewById(R.id.btn_add)

        dbRef = FirebaseDatabase.getInstance().getReference("Deliveries")

        btnAdd.setOnClickListener{
            saveDeliveryData()
        }

    }

    private fun saveDeliveryData() {
        //getting values
        val ProName = productName.text.toString()
        val Address = address.text.toString()
        val Date = date.text.toString()

        if (ProName.isEmpty()){
            productName.error = "Pleas  enter product name"
        }
        if (Address.isEmpty()){
            address.error = "Pleas  enter country"
        }
        if (Date.isEmpty()){
            date.error = "Pleas  enter delivery date"
        }

        val deliveryID = dbRef.push().key!!

        val delivery = DeliveryModel(deliveryID, ProName, Address, Date)

        dbRef.child(deliveryID).setValue(delivery)
            .addOnCompleteListener{
                Toast.makeText(this,"Data added successfully", Toast.LENGTH_LONG).show()

                productName.text.clear()
                address.text.clear()
                date.text.clear()

            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

    fun navigateback(view: View) {
        val intent = Intent(this, DeliveryDashboardActivity::class.java)
        startActivity(intent)
    }
}