package com.mad.mad_encounters.ordermangement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mad.mad_encounters.R
import com.mad.mad_encounters.ordermangement.RejectedOrders
import com.mad.mad_encounters.ordermangement.model.OrderStatus

class RejectedOrderAdapter (private val rejectedList: ArrayList<OrderStatus>) : RecyclerView.Adapter<RejectedOrderAdapter.RejectedOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RejectedOrderViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rejected_list_item, parent, false)
        return RejectedOrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RejectedOrderViewHolder, position: Int) {
        val currentOrder = rejectedList[position]
        holder.tvOrderID.text = currentOrder.orderID
        holder.tvOrderName.text = currentOrder.orderName
        holder.tvStatus.text = currentOrder.status
    }

    override fun getItemCount(): Int {
        return rejectedList.size
    }

    class RejectedOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvOrderID = itemView.findViewById<TextView>(R.id.orderID)
        val tvOrderName = itemView.findViewById<TextView>(R.id.orderTitle)
        val tvStatus = itemView.findViewById<TextView>(R.id.orderStatus)
        val btnDelete = itemView.findViewById<Button>(R.id.deleteBtnRej)
        val btnApprove = itemView.findViewById<Button>(R.id.approveBtnrej)
        init {
            btnDelete.setOnClickListener {
                val adapterPosition = adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    // Call the delete function on the adapter with the current position
                    (itemView.context as RejectedOrders).deleteOrder(adapterPosition)
                }
            }
            btnApprove.setOnClickListener{
                val adapterPosition = adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    // Call the delete function on the adapter with the current position
                    (itemView.context as RejectedOrders).approveOrder(adapterPosition)
                }
            }
        }
    }
}