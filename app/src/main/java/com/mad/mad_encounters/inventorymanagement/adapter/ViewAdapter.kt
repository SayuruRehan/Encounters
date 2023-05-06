package com.mad.mad_encounters.inventorymanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mad.mad_encounters.R
import com.mad.mad_encounters.inventorymanagement.model.InventoryItem

class ViewAdapter(private val view : ArrayList<InventoryItem>) : RecyclerView.Adapter<ViewAdapter.ViewViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.inventory_list_item, parent, false)
        return ViewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewViewHolder, position: Int) {
        val currentItem = view[position]
        holder.cusId.text = currentItem.cusId
        holder.cusName.text = currentItem.cusName
        holder.item.text = currentItem.item
        holder.quantity.text = currentItem.quantity
        holder.cusCountry.text = currentItem.cusCountry
    }

    override fun getItemCount(): Int {
        return view.size
    }

    class ViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cusId = itemView.findViewById<android.widget.TextView>(R.id.cusId)
        val cusName = itemView.findViewById<android.widget.TextView>(R.id.cusName)
        val item = itemView.findViewById<android.widget.TextView>(R.id.item)
        val quantity = itemView.findViewById<android.widget.TextView>(R.id.quantity)
        val cusCountry = itemView.findViewById<android.widget.TextView>(R.id.cusCountry)
        val viewBtn = itemView.findViewById<android.widget.Button>(R.id.viewBtn)

        init {
            viewBtn.setOnClickListener {
                val adapterPosition = adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    // Call the view function on the adapter with the current position
//                    (itemView.context as com.mad.mad_encounters.inventorymanagement.activities.ViewInventory).viewOrder(adapterPosition)
                }
            }
        }


    }
}