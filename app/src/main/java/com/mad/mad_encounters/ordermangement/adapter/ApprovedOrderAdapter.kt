package com.mad.mad_encounters.ordermangement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mad.mad_encounters.R
import com.mad.mad_encounters.ordermangement.ApprovedOrders
import com.mad.mad_encounters.ordermangement.RejectedOrders
import com.mad.mad_encounters.ordermangement.model.OrderStatus

class ApprovedOrderAdapter (private val approvedList: ArrayList<OrderStatus>) : RecyclerView.Adapter<ApprovedOrderAdapter.ApprovedOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApprovedOrderViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.approved_list_item, parent, false)
        return ApprovedOrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ApprovedOrderViewHolder, position: Int) {
        val currentOrder = approvedList[position]
        holder.tvOrderID.text = currentOrder.orderID
        holder.tvOrderName.text = currentOrder.orderName
    }

    override fun getItemCount(): Int {
        return approvedList.size
    }

    class ApprovedOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvOrderID = itemView.findViewById<TextView>(R.id.orderID)
        val tvOrderName = itemView.findViewById<TextView>(R.id.orderTitle)
        val btnDelete = itemView.findViewById<TextView>(R.id.deleteBtnapp)

        init {
            btnDelete.setOnClickListener {
                val adapterPosition = adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    // Call the delete function on the adapter with the current position
                    (itemView.context as ApprovedOrders).deleteOrder(adapterPosition)
                }
            }
        }
    }
}