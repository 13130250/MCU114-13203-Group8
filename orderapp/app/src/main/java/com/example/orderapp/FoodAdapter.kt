package com.example.orderapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private val foods: List<FoodItem>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.txtFoodName)
        val txtPrice: TextView = view.findViewById(R.id.txtFoodPrice)
        val txtQty: TextView = view.findViewById(R.id.txtQty)
        val btnAdd: Button = view.findViewById(R.id.btnAdd)
        val btnMinus: Button = view.findViewById(R.id.btnMinus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_food_item, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = foods[position]
        holder.txtName.text = item.name
        holder.txtPrice.text = "NT$${item.price}"
        holder.txtQty.text = item.quantity.toString()

        holder.btnAdd.setOnClickListener {
            item.quantity++
            holder.txtQty.text = item.quantity.toString()
        }

        holder.btnMinus.setOnClickListener {
            if (item.quantity > 0) item.quantity--
            holder.txtQty.text = item.quantity.toString()
        }
    }

    override fun getItemCount() = foods.size
}
