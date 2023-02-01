package com.peter.carpark.util

import android.content.Context

object SharedPreferencesTools {
    private const val STORAGE_USER_INFO = "USER_INFO"
    fun getUserEmail(context: Context): String? {
        return context.getSharedPreferences(STORAGE_USER_INFO, Context.MODE_PRIVATE)
            .getString("USER_EMAIL", "")
    }

    fun setUserEmail(context: Context, value: String) {
        context.getSharedPreferences(STORAGE_USER_INFO, Context.MODE_PRIVATE).edit()
            .putString("USER_EMAIL", value).apply()
    }
    fun getUserObjectId(context: Context): String? {
        return context.getSharedPreferences(STORAGE_USER_INFO, Context.MODE_PRIVATE)
            .getString("USER_OBJECT_ID", null)
    }

    fun setUserObjectId(context: Context, value: String) {
        context.getSharedPreferences(STORAGE_USER_INFO, Context.MODE_PRIVATE).edit()
            .putString("USER_OBJECT_ID", value).apply()
    }
    fun getUserSession(context: Context): String? {
        return context.getSharedPreferences(STORAGE_USER_INFO, Context.MODE_PRIVATE)
            .getString("USER_SESSION", null)
    }

    fun setUserSession(context: Context, value: String) {
        context.getSharedPreferences(STORAGE_USER_INFO, Context.MODE_PRIVATE).edit()
            .putString("USER_SESSION", value).apply()
    }


}