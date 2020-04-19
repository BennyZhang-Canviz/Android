package com.example.navigationwithviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel(){
    var number: MutableLiveData<Int> = MutableLiveData(66)

    fun getNumberResult () :  MutableLiveData<Int>{
        return number
    }
    fun add(x:Int){
        number.value = (number.value?:0) + x
    }
}