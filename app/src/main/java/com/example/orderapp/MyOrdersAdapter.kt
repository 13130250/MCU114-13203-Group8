package com.example.orderapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MyOrdersAdapter(
    private val orders: List<OrderHistory>
) : RecyclerView.Adapter<MyOrdersAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val tvItems: TextView = itemView.findViewById(R.id.tvItems)
        val tvTotal: TextView = itemView.findViewById(R.id.tvTotal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders[position]

        val fmt = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.TAIWAN)
        holder.tvTime.text = fmt.format(Date(order.createdAt))

        holder.tvItems.text = order.items
        holder.tvTotal.text = "總額：NT$${order.total}"
    }

    override fun getItemCount(): Int = orders.size
}

