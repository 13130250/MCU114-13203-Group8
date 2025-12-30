package com.example.orderapp

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(
    private var foods: List<FoodItem>,
    private val onAddToCart: (FoodItem, View) -> Unit // 點擊加入購物車回調
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.txtFoodName)
        val txtPrice: TextView = view.findViewById(R.id.txtFoodPrice)
        val txtQty: TextView = view.findViewById(R.id.txtQty)
        val btnAdd: ImageButton = view.findViewById(R.id.btnAdd)
        val btnMinus: ImageButton = view.findViewById(R.id.btnMinus)
        val btnAddCart: Button = view.findViewById(R.id.btnAddCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_food_item, parent, false)
        return FoodViewHolder(v)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = foods[position]

        holder.txtName.text = item.name
        holder.txtPrice.text = "NT$${item.price}"
        holder.txtQty.text = item.quantity.toString()

        // "+" 按鈕
        holder.btnAdd.setOnClickListener {
            item.quantity++
            holder.txtQty.text = item.quantity.toString()
            // 小動畫：按下放大再縮回
            val animX = ObjectAnimator.ofFloat(holder.btnAdd, "scaleX", 1f, 1.2f, 1f)
            val animY = ObjectAnimator.ofFloat(holder.btnAdd, "scaleY", 1f, 1.2f, 1f)
            animX.duration = 250
            animY.duration = 250
            animX.interpolator = AccelerateDecelerateInterpolator()
            animY.interpolator = AccelerateDecelerateInterpolator()
            animX.start()
            animY.start()
        }

        // "-" 按鈕
        holder.btnMinus.setOnClickListener {
            if (item.quantity > 0) {
                item.quantity--
                holder.txtQty.text = item.quantity.toString()
            }
        }

        // "加入購物車" 按鈕
        holder.btnAddCart.setOnClickListener { v ->
            if (item.quantity <= 0) {
                // 數量為 0 不加入
                return@setOnClickListener
            }

            onAddToCart(item.copy(), v)

            // 縮放動畫作為反饋
            val animX = ObjectAnimator.ofFloat(holder.btnAddCart, "scaleX", 1f, 0.85f, 1f)
            val animY = ObjectAnimator.ofFloat(holder.btnAddCart, "scaleY", 1f, 0.85f, 1f)
            animX.duration = 300
            animY.duration = 300
            animX.start()
            animY.start()
        }
    }

    override fun getItemCount(): Int = foods.size

    // 更新列表（例如分類過濾後）
    fun updateList(newList: List<FoodItem>) {
        foods = newList
        notifyDataSetChanged()
    }
}
