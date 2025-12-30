package com.example.orderapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FeedbackListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_list)

        val rvOrders = findViewById<RecyclerView>(R.id.rvOrders)
        rvOrders.layoutManager = LinearLayoutManager(this)

        val sharedPref = getSharedPreferences("feedback_prefs", Context.MODE_PRIVATE)
        val savedFeedback = sharedPref.getString("feedback_list", "")

        val feedbackList = savedFeedback?.split("\n")?.map { entry ->
            val parts = entry.split("|")
            val time = parts.getOrNull(0) ?: ""
            val account = parts.getOrNull(1) ?: ""
            val content = parts.getOrNull(2) ?: ""
            Feedback(time, account, content)
        } ?: listOf()

        val adapter = FeedbackAdapter(feedbackList)
        rvOrders.adapter = adapter
    }
}
