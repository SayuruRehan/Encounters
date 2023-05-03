package com.mad.mad_encounters.ordermangement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mad.mad_encounters.R
import com.mad.mad_encounters.ordermangement.model.OrderStatus

class ApproveForm : AppCompatActivity() {

    private lateinit var etOrderID : EditText
    private lateinit var etOrderName : EditText
    private lateinit var etOrderCountry : EditText
    private lateinit var etOrderMode : Spinner
    private lateinit var dbRef : DatabaseReference
    private lateinit var btnSubmit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_approve_form)

        etOrderID = findViewById(R.id.editTextOrderID)
        etOrderName = findViewById(R.id.editTextOrderName)
        etOrderCountry = findViewById(R.id.editTextCountry)
        etOrderMode = findViewById(R.id.editTextMode)
        btnSubmit = findViewById(R.id.buttonSubmit)

        dbRef = FirebaseDatabase.getInstance().getReference("ApprovedOrders")
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
        val orderCountry = etOrderCountry.text.toString().trim()
        val orderMode = etOrderMode.selectedItem.toString().trim()

        if (orderID.isEmpty()) {
            etOrderID.error = "Please enter an order ID"
            return
        }
        if (orderName.isEmpty()) {
            etOrderName.error = "Please enter an order name"
            return
        }
        if (orderCountry.isEmpty()) {
            etOrderCountry.error = "Please enter an order country"
            return
        }
        if (orderMode.isEmpty()) {
            etOrderName.error = "Please enter an order mode"
            return
        }
        //insert data into database
        val acceptedOrder = OrderStatus(
            orderID,
            orderName,
            orderCountry,
            orderMode,
            "Accepted"
        )
        dbRef.child(orderID).setValue(acceptedOrder).addOnCompleteListener {
            Toast.makeText(this, "Order Accepted", Toast.LENGTH_SHORT).show()
            etOrderID.setText("")
            etOrderName.setText("")
            etOrderCountry.setText("")
            etOrderMode.setSelection(0)
        }.addOnFailureListener{ err ->
            Toast.makeText(this, "Error: ${err.message}", Toast.LENGTH_SHORT).show()
        }
    }
}