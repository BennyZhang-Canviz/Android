package com.example.room

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHelper(var activity: Activity){

    private val SP_KEY = "sp_key"
    private val USE_CARD_KEY = "USE_CARD_KEY"
    private var shp:SharedPreferences = activity.getSharedPreferences(SP_KEY,Context.MODE_PRIVATE)

    fun getShowCard(): Boolean {
        return shp.getBoolean(USE_CARD_KEY,false)
    }

    fun setShowCard(userCard: Boolean){
        shp.edit().putBoolean(USE_CARD_KEY,userCard).apply()
    }
}