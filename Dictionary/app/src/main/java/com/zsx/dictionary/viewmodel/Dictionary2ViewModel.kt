package com.zsx.dictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zsx.dictionary.entity.Dictionary2Entity
import com.zsx.dictionary.network.MainNetwork
import kotlinx.coroutines.launch


class Dictionary2ViewModel(private val network: MainNetwork) : ViewModel() {
    private var _dictionary2Entity : MutableLiveData<List<Dictionary2Entity>> =
        MutableLiveData<List<Dictionary2Entity>>()

    val dictionary2Entity: LiveData<List<Dictionary2Entity>> get() = _dictionary2Entity

    fun getDictionary2(keyword:String){
        viewModelScope.launch {
            var result = network.getDictionary2(keyword)
            if(result.error_code !=1){
                _dictionary2Entity.postValue(result.result)
            }
        }
    }
}