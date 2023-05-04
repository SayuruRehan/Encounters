package com.mad.mad_encounters.deliverymangement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mad.mad_encounters.R
import com.mad.mad_encounters.deliverymangement.model.ApproveModel

class ApprovedOrderAdapter (private val approvedList: ArrayList<ApproveModel>) : RecyclerView.Adapter<ApprovedOrderAdapter.ApprovedOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApprovedOrderViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.approved_delivery_item, parent, false)
        return ApprovedOrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ApprovedOrderViewHolder, position: Int) {
        val currentOrder = approvedList[position]
        holder.tvOrderID.text = currentOrder.orderID
        holder.tvOrderName.text = currentOrder.orderName
        holder.tvOrderCountry.text = currentOrder.orderCountry
        holder.tvOrderMode.text = currentOrder.orderMode
    }

    override fun getItemCount(): Int {
        return approvedList.size
    }

    class ApprovedOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvOrderID = itemView.findViewById<TextView>(R.id.orderID)
        val tvOrderName = itemView.findViewById<TextView>(R.id.orderTitle)
        val tvOrderCountry = itemView.findViewById<TextView>(R.id.orderCountry)
        val tvOrderMode = itemView.findViewById<TextView>(R.id.orderMode)

    }
}