package com.example.gamesetmatch.util

import android.content.Context

object sharedPreference {

    fun startSession(context: Context, username: String){
        val authPref = context.getSharedPreferences(Constants.USER_SESSION, Context.MODE_PRIVATE)
        val editor = authPref.edit()
        editor.putBoolean(Constants.USER_SIGNED_IN, true)
        editor.putString(Constants.USERNAME, username)
        editor.apply()
    }

    fun isSessionActive(context: Context): Boolean{
        val authPref = context.getSharedPreferences(Constants.USER_SESSION, Context.MODE_PRIVATE)
        return authPref.getBoolean(Constants.USER_SIGNED_IN, false)
    }

    fun getUsername(context: Context): String{
        val authPref = context.getSharedPreferences(Constants.USER_SESSION, Context.MODE_PRIVATE)
        return authPref.getString(Constants.USERNAME, "No user")!!
    }
}