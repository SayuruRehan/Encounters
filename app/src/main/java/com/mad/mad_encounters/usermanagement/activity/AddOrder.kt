package com.mad.mad_encounters.usermanagement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mad.mad_encounters.Home
import com.mad.mad_encounters.R
import com.mad.mad_encounters.usermanagement.model.OrderModel

class AddOrder : AppCompatActivity() {

    private lateinit var productName:  EditText
    private lateinit var contactNumber:  EditText
    private lateinit var delDate: EditText
    private lateinit var destAddress: EditText
    private lateinit var country: EditText
    private lateinit var postalcode: EditText
    private lateinit var quantity: EditText
    private lateinit var btnAdd: Button

    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)

        productName = findViewById(R.id.tv_productname)
        contactNumber = findViewById(R.id.tv_contactNumber)
        delDate = findViewById(R.id.tv_delDate)
        destAddress = findViewById(R.id.tv_destAddress)
        country = findViewById(R.id.tv_country)
        postalcode = findViewById(R.id.tv_postalCode)
        quantity = findViewById(R.id.tv_quantity)
        btnAdd = findViewById(R.id.btn_addOrder)

        dbRef = FirebaseDatabase.getInstance().getReference("Orders")

        btnAdd.setOnClickListener{
            saveOrderData()
        }
    }

    private fun saveOrderData() {
        var ProductName = productName.text.toString()
        var ContactNumber = contactNumber.text.toString()
        var DelDate = delDate.text.toString()
        var DestAddress = destAddress.text.toString()
        var Country = country.text.toString()
        var PostalCode = postalcode.text.toString()
        var Quantity = quantity.text.toString()

        if (ProductName.isEmpty()){
            productName.error = "Please enter product name"
        }
        if (ContactNumber.isEmpty()){
            contactNumber.error = "Please enter contact number"
        }
        if (DelDate.isEmpty()){
            delDate.error = "Please enter delivery date"
        }
        if (DestAddress.isEmpty()){
            destAddress.error = "Please enter destination address"
        }
        if (Country.isEmpty()){
            country.error = "Please enter country"
        }
        if (PostalCode.isEmpty()){
            postalcode.error = "Please enter postal code"
        }
        if (Quantity.isEmpty()){
            quantity.error = "Please enter quantity"
        }

        val orderID = dbRef.push().key!!

        val order = OrderModel(orderID, ProductName, ContactNumber, DelDate, DestAddress, Country, PostalCode, Quantity)

        dbRef.child(orderID).setValue(order)
            .addOnCompleteListener{
                Toast.makeText(applicationContext, "Order added successfully", Toast.LENGTH_SHORT).show()

                productName.text.clear()
                contactNumber.text.clear()
                delDate.text.clear()
                destAddress.text.clear()
                country.text.clear()
                postalcode.text.clear()
                quantity.text.clear()
            }.addOnFailureListener { err ->
                Toast.makeText(applicationContext, "Order added failed", Toast.LENGTH_SHORT).show()
            }
    }

    fun navigateback(view: View) {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }
}