package com.mad.mad_encounters.inventorymanagement.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mad.mad_encounters.R
import com.mad.mad_encounters.inventorymanagement.model.InventoryItem

class AddInventory : AppCompatActivity() {

    private lateinit var etCusId : EditText
    private lateinit var etCusName : EditText
    private lateinit var etItem : EditText
    private lateinit var etQuantity : EditText
    private lateinit var etCusCountry : EditText
    private lateinit var btnSubmit : Button
    private lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inventory)

        etCusId = findViewById(R.id.cusId)
        etCusName = findViewById(R.id.cusName)
        etItem = findViewById(R.id.item)
        etQuantity = findViewById(R.id.quantity)
        etCusCountry = findViewById(R.id.cusCountry)
        btnSubmit = findViewById(R.id.submitBtn)

        dbRef = FirebaseDatabase.getInstance().getReference("InventoryItems")
        btnSubmit.setOnClickListener{
            saveInventoryItem()
        }

    }
    private fun saveInventoryItem(){
        val cusId = etCusId.text.toString().trim()
        val cusName = etCusName.text.toString().trim()
        val item = etItem.text.toString().trim()
        val quantity = etQuantity.text.toString().trim()
        val cusCountry = etCusCountry.text.toString().trim()

        if (cusId.isEmpty()) {
            etCusId.error = "Please enter a customer ID"
            return
        }
        if (cusName.isEmpty()) {
            etCusName.error = "Please enter a customer name"
            return
        }
        if (item.isEmpty()) {
            etItem.error = "Please enter an item"
            return
        }
        if (quantity.isEmpty())  {
            etQuantity.error = "Quantity should be a number > 0"
            return
        }
        if (cusCountry.isEmpty()) {
            etCusCountry.error = "Please enter a customer country"
            return
        }

        val addedItem = InventoryItem(
            cusId = cusId,
            cusName = cusName,
            item = item,
            quantity = quantity,
            cusCountry = cusCountry
        )
        dbRef.child(cusId).setValue(addedItem).addOnCompleteListener {
            Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show()
            etCusId.setText("")
            etCusName.setText("")
            etItem.setText("")
            etQuantity.setText("")
            etCusCountry.setText("")
        }.addOnFailureListener{ err ->
            Toast.makeText(this, "Error: ${err.message}", Toast.LENGTH_SHORT).show()
        }


    }


}