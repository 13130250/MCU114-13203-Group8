package com.example.orderapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        // 使用者表（登入用）
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                email TEXT UNIQUE,
                password TEXT
            )
            """.trimIndent()
        )

        // 訂單表（重點：一定要有 user）
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS orders (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user TEXT NOT NULL,
                created_at INTEGER NOT NULL,
                items TEXT NOT NULL,
                total INTEGER NOT NULL
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // 作業版本：直接重建即可
        db.execSQL("DROP TABLE IF EXISTS orders")
        onCreate(db)
    }

    // =========================
    // 訂單相關
    // =========================

    /** 存訂單（綁定帳號） */
    fun insertOrder(user: String, itemsText: String, total: Int): Long {
        val values = ContentValues().apply {
            put("user", user)
            put("created_at", System.currentTimeMillis())
            put("items", itemsText)
            put("total", total)
        }
        return writableDatabase.insert("orders", null, values)
    }

    /** 只取得某個帳號的訂單 */
    fun getOrdersByUser(user: String): List<OrderHistory> {
        val list = mutableListOf<OrderHistory>()
        val cursor = readableDatabase.rawQuery(
            "SELECT id, created_at, items, total FROM orders WHERE user = ? ORDER BY id DESC",
            arrayOf(user)
        )

        cursor.use {
            while (it.moveToNext()) {
                list.add(
                    OrderHistory(
                        id = it.getLong(0),
                        createdAt = it.getLong(1),
                        items = it.getString(2),
                        total = it.getInt(3)
                    )
                )
            }
        }
        return list
    }

    companion object {
        // ✅ 一定要有，否則會 Unresolved reference
        private const val DB_NAME = "orderapp.db"

        // ✅ 有改資料表結構（加 user）一定要升版
        private const val DB_VERSION = 2
    }
}
