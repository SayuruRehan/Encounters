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

class RejectForm : AppCompatActivity() {

    private lateinit var etOrderID : EditText
    private lateinit var etOrderName : EditText
    private lateinit var etReason : EditText
    private lateinit var dbRef : DatabaseReference
    private lateinit var btnSubmit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reject_form)

        etOrderID = findViewById(R.id.editTextOrderID)
        etOrderName = findViewById(R.id.editTextOrderName)
        etReason = findViewById(R.id.editTextReason)
        btnSubmit = findViewById(R.id.buttonSubmit)

        dbRef = FirebaseDatabase.getInstance().getReference("RejectedOrders")
        btnSubmit.setOnClickListener{
            saveRejectedOrder()
        }
        val backButton = findViewById<ImageView>(R.id.backBtn)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun saveRejectedOrder() {
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
        if (reason.isEmpty()) {
            etReason.error = "Please enter a reason"
            return
        }

        val rejectedOrder = OrderStatus(orderID, orderName, reason, "Rejected")
        dbRef.child(orderID).setValue(rejectedOrder).addOnCompleteListener {
            Toast.makeText(this, "Order Rejected", Toast.LENGTH_SHORT).show()
            etOrderID.setText("")
            etOrderName.setText("")
            etReason.setText("")
        }.addOnFailureListener{ err ->
            Toast.makeText(this, "Error: ${err.message}", Toast.LENGTH_SHORT).show()
        }
    }

}