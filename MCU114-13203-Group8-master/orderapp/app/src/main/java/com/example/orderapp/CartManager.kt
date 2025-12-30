package com.example.orderapp

object CartManager {
    private val items = mutableListOf<FoodItem>()

    fun add(item: FoodItem) {
        val found = items.find { it.name == item.name && it.price == item.price }
        if (found != null) {
            found.quantity += item.quantity
        } else {
            items.add(item.copy())
        }
    }

    fun setQuantity(name: String, qty: Int) {
        val found = items.find { it.name == name }
        if (found != null) {
            found.quantity = qty
            if (found.quantity <= 0) items.remove(found)
        }
    }

    fun remove(name: String) {
        items.removeAll { it.name == name }
    }

    fun getItems(): List<FoodItem> = items.toList()

    fun clear() { items.clear() }

    fun total(): Int = items.sumOf { it.price * it.quantity }
}
