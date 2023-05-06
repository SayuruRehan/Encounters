package com.mad.mad_encounters.inventorymanagement.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mad.mad_encounters.R

class InventoryMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_main)

        val addNewItemBtn = findViewById<Button>(R.id.addNewItemBtn)
        addNewItemBtn.setOnClickListener{
            val intent = Intent(this, AddInventory::class.java)
            startActivity(intent)
        }

        val updateItemBtn = findViewById<Button>(R.id.updateItemBtn)
        updateItemBtn.setOnClickListener{
            val intent = Intent(this, UpdateInventory::class.java)
            startActivity(intent)
        }

        val deleteItemBtn = findViewById<Button>(R.id.deleteItemBtn)
        deleteItemBtn.setOnClickListener{
            val intent = Intent(this, DeleteInventory::class.java)
            startActivity(intent)
        }

        val viewAllItemBtn = findViewById<Button>(R.id.viewAllItemBtn)
        viewAllItemBtn.setOnClickListener{
            val intent = Intent(this, ViewInventory::class.java)
            startActivity(intent)
        }
    }
}