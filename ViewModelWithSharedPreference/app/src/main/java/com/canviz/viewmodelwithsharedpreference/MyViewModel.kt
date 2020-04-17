package com.canviz.viewmodelwithsharedpreference

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle


class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val SharedPreferencesKey = "spkey"
    private val SavedNumberKey = "saved_number"
    private var number: MutableLiveData<Int> = MutableLiveData(0)
    private val _application = application
    init {
        loadNumber()
    }

    private fun loadNumber(){
        val x = _application.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE)
            .getInt(SavedNumberKey, 0)
        number.value = x
    }
    fun getResult(): MutableLiveData<Int> {
        return number
    }

    fun saveResult() {
        _application.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE).edit()
            .putInt(SavedNumberKey, (number.value ?: 0)).apply()
    }

    fun add(x: Int) {
        val result = (getResult().value?:0)+x
        number.value = result
    }

}