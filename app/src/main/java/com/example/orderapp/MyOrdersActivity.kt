package com.example.orderapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class MyOrdersActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var tvEmpty: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders)

        rv = findViewById(R.id.rvOrders)
        tvEmpty = findViewById(R.id.tvEmpty)

        rv.layoutManager = LinearLayoutManager(this)
        loadOrders()
    }

    override fun onResume() {
        super.onResume()
        loadOrders()
    }

    private fun loadOrders() {
        val user = SessionManager.getCurrentUser(this)
        if (user == null) {
            tvEmpty.visibility = View.VISIBLE
            rv.visibility = View.GONE
            return
        }
        val orders = DBHelper(this).getOrdersByUser(user)

        if (orders.isEmpty()) {
            tvEmpty.visibility = View.VISIBLE
            rv.visibility = View.GONE
        } else {
            tvEmpty.visibility = View.GONE
            rv.visibility = View.VISIBLE
            rv.adapter = MyOrdersAdapter(orders)
        }
    }
}

