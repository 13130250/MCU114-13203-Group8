package com.example.orderapp

import android.content.Context

object SessionManager {
    private const val PREF = "session"
    private const val KEY_CURRENT_USER = "current_user"

    fun setCurrentUser(context: Context, username: String) {
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_CURRENT_USER, username)
            .apply()
    }

    fun getCurrentUser(context: Context): String? {
        return context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .getString(KEY_CURRENT_USER, null)
    }

    fun logout(context: Context) {
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit()
            .remove(KEY_CURRENT_USER)
            .apply()
    }
}
