package com.mad.mad_encounters.deliverymangement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mad.mad_encounters.R
import com.mad.mad_encounters.deliverymangement.model.DeliveryModel

class DeliveryAdapter (private val deliveryList: ArrayList<DeliveryModel>) : RecyclerView.Adapter<DeliveryAdapter.ViewHolder>(){

    private lateinit var dListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        dListener = clickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.delivery_list_item, parent, false)
        return ViewHolder(itemView, dListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDelivery = deliveryList[position]
        holder.tvDeliveryName.text = currentDelivery.ProName
        holder.tvDeliveryAddress.text = currentDelivery.Address
        holder.tvDeliveryDate.text = currentDelivery.Date
        holder.tvDeliveryType.text = currentDelivery.DeliveryType
    }

    override fun getItemCount(): Int {
        return deliveryList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvDeliveryName : TextView = itemView.findViewById(R.id.tvDeliveryName)
        val tvDeliveryAddress : TextView = itemView.findViewById(R.id.tvDeliveryAddress)
        val tvDeliveryDate : TextView = itemView.findViewById(R.id.tvDeliveryDate)
        val tvDeliveryType : TextView = itemView.findViewById(R.id.tvDeliveryType)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }


}