package com.mad.mad_encounters.ordermangement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mad.mad_encounters.R
import com.mad.mad_encounters.ordermangement.model.OrderStatus

class RejectedOrderAdapter (private val approvedList: ArrayList<OrderStatus>) : RecyclerView.Adapter<RejectedOrderAdapter.RejectedOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RejectedOrderViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.approved_list_item, parent, false)
        return RejectedOrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RejectedOrderViewHolder, position: Int) {
        val currentOrder = approvedList[position]
        holder.tvOrderID.text = currentOrder.orderID
        holder.tvOrderName.text = currentOrder.orderName
    }

    override fun getItemCount(): Int {
        return approvedList.size
    }

    class RejectedOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvOrderID = itemView.findViewById<TextView>(R.id.orderID)
        val tvOrderName = itemView.findViewById<TextView>(R.id.orderTitle)
    }
}