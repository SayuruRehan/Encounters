package com.mad.mad_encounters.deliverymangement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import com.mad.mad_encounters.R
import com.mad.mad_encounters.inventorymanagement.activities.InventoryMain
import com.mad.mad_encounters.ordermangement.activities.OrderManagementMain
import com.mad.mad_encounters.usermanagement.activity.Home

class DeliveryDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delivery_dashboard)

        setupMenu()
    }
    fun navigateToAdd(view: View) {
        val intent = Intent(this, AddDeliveryActivity::class.java)
        startActivity(intent)
    }

    fun navigateToAll(view: View) {
        val intent = Intent(this, AllDeliveryOrdersActivity::class.java)
        startActivity(intent)
    }

    fun navigateToApproved(view: View) {
        val intent = Intent(this, ApprovedDeliveryDisplayActivity::class.java)
        startActivity(intent)
    }

    private fun setupMenu() {
        val optionsMenu = findViewById<ImageView>(R.id.optionsMenu)
        optionsMenu.setOnClickListener {
            showPopupMenu(optionsMenu)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }
    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.options_menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_user_management -> {
                    Toast.makeText(this, "User Management", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_order_management-> {
                    Toast.makeText(this, "Order Management", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, OrderManagementMain::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menu_delivery_management -> {
                    Toast.makeText(this, "Delivery Management", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, DeliveryDashboardActivity::class.java)
                    true
                }
                R.id.menu_inventory_management -> {
                    Toast.makeText(this, "Inventory Management", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, InventoryMain::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }

        popupMenu.show()
    }
}
