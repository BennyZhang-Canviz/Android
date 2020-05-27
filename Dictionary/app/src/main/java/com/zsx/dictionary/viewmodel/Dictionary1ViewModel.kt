package com.zsx.dictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zsx.dictionary.entity.Dictionary1Entity
import com.zsx.dictionary.network.MainNetwork
import kotlinx.coroutines.launch

class Dictionary1ViewModel(private val network: MainNetwork) : ViewModel() {
    private var _dictionary1Entity : MutableLiveData<List<Dictionary1Entity>> =
        MutableLiveData<List<Dictionary1Entity>>()

    val dictionary1Entity:LiveData<List<Dictionary1Entity>> get() = _dictionary1Entity

    fun getDictionary1(keyword:String){
        viewModelScope.launch {
            var result = network.getDictionary1(keyword)
            if(result.error_code !=1){
                _dictionary1Entity.postValue(result.result)
            }
        }
    }
}