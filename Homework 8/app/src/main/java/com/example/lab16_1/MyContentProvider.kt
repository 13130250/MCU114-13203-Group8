package com.example.lab16_1

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class MyContentProvider : ContentProvider() {
    private lateinit var dbrw: SQLiteDatabase

    override fun onCreate(): Boolean {
        val context = context ?: return false
        dbrw = MyDBHelper(context).writableDatabase
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val rowId = dbrw.insert("myTable", null, values)
        return Uri.parse("content://com.example.lab16/$rowId")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        return dbrw.update("myTable", values, "brand=?", arrayOf(selection))
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return dbrw.delete("myTable", "brand=?", arrayOf(selection))
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val where = if (selection == null) null else "brand='$selection'"
        return dbrw.query("myTable", null, where, null, null, null, null)
    }

    override fun getType(uri: Uri): String? = null
}
