package com.mad.mad_encounters.inventorymanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mad.mad_encounters.R
import com.mad.mad_encounters.inventorymanagement.model.InventoryItem

class ViewAdapter(private val viewItems : ArrayList<InventoryItem>) : RecyclerView.Adapter<ViewAdapter.ViewViewHolder>(){

    private lateinit var iListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnITemClickListener(clickListener: onItemClickListener){
        iListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.inventory_list_item, parent, false)
        return ViewViewHolder(itemView, iListener)
    }

    override fun onBindViewHolder(holder: ViewViewHolder, position: Int) {
        val currentItem = viewItems[position]
        holder.cusId.text = currentItem.cusId
//        holder.cusName.text = currentItem.cusName
        holder.item.text = currentItem.item
        holder.quantity.text = currentItem.quantity
//        holder.cusCountry.text = currentItem.cusCountry
    }

    override fun getItemCount(): Int {
        return viewItems.size
    }

    class ViewViewHolder(itemView: View, clickListener: onItemClickListener ) : RecyclerView.ViewHolder(itemView) {
        val cusId = itemView.findViewById<TextView>(R.id.editTextTextPersonName6)
//        val cusName = itemView.findViewById<android.widget.TextView>(R.id.cusName)
        val item = itemView.findViewById<TextView>(R.id.textView2)
        val quantity = itemView.findViewById<TextView>(R.id.tvQty)
//        val cusCountry = itemView.findViewById<android.widget.TextView>(R.id.cusCountry)
//        val viewBtn = itemView.findViewById<android.widget.Button>(R.id.viewBtn)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }


    }
}