package com.mad.mad_encounters.deliverymangement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mad.mad_encounters.R
import com.mad.mad_encounters.deliverymangement.model.DeliveryModel

class AddDeliveryActivity : AppCompatActivity() {

    private lateinit var productName: EditText
    private lateinit var address: EditText
    private lateinit var date: EditText
    private lateinit var deliveryType: Spinner
    private lateinit var btnAdd: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_delivery)

        productName = findViewById(R.id.tv_product_name)
        address = findViewById(R.id.tv_address)
        date = findViewById(R.id.tv_delivery_date)
        deliveryType = findViewById(R.id.sp_delivery_type)
        btnAdd = findViewById(R.id.btn_add)

        dbRef = FirebaseDatabase.getInstance().getReference("Deliveries")

        btnAdd.setOnClickListener{
            if (isValidDeliveryData()) {
                saveDeliveryData()
            }
        }
    }

    private fun isValidDeliveryData(): Boolean {
        val ProName = productName.text.toString()
        val Address = address.text.toString()
        val Date = date.text.toString()
        val DeliveryType = deliveryType.selectedItem.toString()

        if (ProName.isEmpty()){
            productName.error = "Please enter product name"
            return false
        }
        if (Address.isEmpty()){
            address.error = "Please enter address"
            return false
        }
        if (Date.isEmpty()){
            date.error = "Please enter delivery date"
            return false
        }
        if (DeliveryType == "Select delivery type"){
            Toast.makeText(this, "Please select a delivery type", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun saveDeliveryData() {
        //getting values
        val ProName = productName.text.toString()
        val Address = address.text.toString()
        val Date = date.text.toString()
        val DeliveryType = deliveryType.selectedItem.toString()

        val deliveryID = dbRef.push().key!!

        val delivery = DeliveryModel(deliveryID, ProName, Address, Date, DeliveryType)

        dbRef.child(deliveryID).setValue(delivery)
            .addOnCompleteListener{
                Toast.makeText(this,"Data added successfully", Toast.LENGTH_LONG).show()

                productName.text.clear()
                address.text.clear()
                date.text.clear()
                deliveryType.setSelection(0)

            }.addOnFailureListener { err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

    fun navigateback(view: View) {
        val intent = Intent(this, DeliveryDashboardActivity::class.java)
        startActivity(intent)
    }
}