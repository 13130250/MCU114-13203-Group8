package com.example.orderapp

data class OrderHistory(
    val id: Long,
    val createdAt: Long,
    val items: String,
    val total: Int
)
