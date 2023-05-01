package com.mad.mad_encounters.ordermangement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mad.mad_encounters.R
import com.mad.mad_encounters.ordermangement.model.OrderStatus

class ApproveForm : AppCompatActivity() {

    private lateinit var etOrderID : EditText
    private lateinit var etOrderName : EditText
    private lateinit var etReason : EditText
    private lateinit var dbRef : DatabaseReference
    private lateinit var btnSubmit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_approve_form)

        etOrderID = findViewById(R.id.editTextOrderID)
        etOrderName = findViewById(R.id.editTextOrderName)
        btnSubmit = findViewById(R.id.buttonSubmit)

        dbRef = FirebaseDatabase.getInstance().getReference("AcceptedOrders")
        btnSubmit.setOnClickListener{
            saveAcceptedOrder()
        }

        val backButton = findViewById<ImageView>(R.id.backBtn)
        backButton.setOnClickListener {
            finish()
        }
    }
    private fun saveAcceptedOrder() {
        val orderID = etOrderID.text.toString().trim()
        val orderName = etOrderName.text.toString().trim()
        val reason = etReason.text.toString().trim()

        if (orderID.isEmpty()) {
            etOrderID.error = "Please enter an order ID"
            return
        }
        if (orderName.isEmpty()) {
            etOrderName.error = "Please enter an order name"
            return
        }

        val rejectedOrder = OrderStatus(orderID, orderName, reason, "Accepted")
        dbRef.child(orderID).setValue(rejectedOrder).addOnCompleteListener {
            Toast.makeText(this, "Order Accepted", Toast.LENGTH_SHORT).show()
            etOrderID.setText("")
            etOrderName.setText("")
            etReason.setText("")
        }.addOnFailureListener{ err ->
            Toast.makeText(this, "Error: ${err.message}", Toast.LENGTH_SHORT).show()
        }
    }
}