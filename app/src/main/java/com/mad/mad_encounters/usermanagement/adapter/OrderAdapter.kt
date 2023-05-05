package com.mad.mad_encounters.usermanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mad.mad_encounters.R
import com.mad.mad_encounters.usermanagement.model.OrderModel

class OrderAdapter (private val orderList: ArrayList<OrderModel>) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private lateinit var listener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener) {
        listener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_list_item, parent, false)
        return ViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentOrder = orderList[position]
        holder.orderId.text = currentOrder.orderID
        holder.tvOrderAddress.text = currentOrder.DestAddress
        holder.tvOrderName.text = currentOrder.ProductName
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val orderId: TextView = itemView.findViewById(R.id.orderId)
        val tvOrderName: TextView = itemView.findViewById(R.id.tvOrderName)
        val tvOrderAddress: TextView = itemView.findViewById(R.id.tvOrderAddress)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}

