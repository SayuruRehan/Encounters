package com.mad.mad_encounters.inventorymanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mad.mad_encounters.R
import com.mad.mad_encounters.inventorymanagement.model.InventoryItem

class ViewAdapter(private val viewItems: ArrayList<InventoryItem>) :
    RecyclerView.Adapter<ViewAdapter.ViewHolder>() {

    private var mListener: onItemClickListener? = null

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.inventory_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = viewItems[position]
        holder.cusId.text = currentItem.cusId
        holder.item.text = currentItem.item
    }

    override fun getItemCount(): Int {
        return viewItems.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener?) : RecyclerView.ViewHolder(itemView) {

        val cusId = itemView.findViewById<TextView>(R.id.editTextTextPersonName6)
        val item = itemView.findViewById<TextView>(R.id.textView2)

        init {
            clickListener?.let {
                itemView.setOnClickListener {
                    clickListener.onItemClick(adapterPosition)
                }
            }
        }

    }
}
