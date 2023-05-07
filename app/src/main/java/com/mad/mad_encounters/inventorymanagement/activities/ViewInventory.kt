package com.mad.mad_encounters.inventorymanagement.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler.Value
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mad.mad_encounters.R
import com.mad.mad_encounters.inventorymanagement.adapter.ViewAdapter
import com.mad.mad_encounters.inventorymanagement.model.InventoryItem

class ViewInventory : AppCompatActivity() {

    private lateinit var appRecyclerView: RecyclerView
    private lateinit var viewItems: ArrayList<InventoryItem>
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_inventory)

        appRecyclerView = findViewById(R.id.recycler)
        appRecyclerView.layoutManager = LinearLayoutManager(this)
        appRecyclerView.setHasFixedSize(true)

        viewItems = arrayListOf<InventoryItem>()
        getInventoryList()

    }
    private fun getInventoryList(){
        appRecyclerView.visibility = View.GONE
        dbRef = FirebaseDatabase.getInstance().getReference("InventoryItems")
        dbRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                viewItems.clear()
                if (snapshot.exists()) {
                    for (item in snapshot.children) {
                        val inventoryItem = item.getValue(InventoryItem::class.java)
                        viewItems.add(inventoryItem!!)
                    }
                    val iAdapter = ViewAdapter(viewItems)
                        appRecyclerView.adapter = iAdapter

                            iAdapter.setOnItemClickListener(object : ViewAdapter.onItemClickListener{
                                override fun onItemClick(position: Int) {
                                    val intent = Intent(this@ViewInventory, InventryDetailsActivity::class.java)

                                    intent.putExtra("InvId", viewItems[position].cusId)
                                    intent.putExtra("InvName", viewItems[position].cusName)
                                    intent.putExtra("InvCon", viewItems[position].cusCountry)
                                    intent.putExtra("InvItem", viewItems[position].item)
                                    startActivity(intent)
                                }

                            })

                    appRecyclerView.adapter = ViewAdapter(viewItems)
                    appRecyclerView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ViewInventory, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
