package com.zsx.dictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zsx.dictionary.entity.Dictionary2Entity
import com.zsx.dictionary.entity.Dictionary3Entity
import com.zsx.dictionary.entity.Dictionary3ResultEntity
import com.zsx.dictionary.network.MainNetwork
import kotlinx.coroutines.launch


class Dictionary3ViewModel(private val network: MainNetwork) : ViewModel() {
    private var _dictionary3Entity : MutableLiveData<Dictionary3ResultEntity> =
        MutableLiveData<Dictionary3ResultEntity>()

    val dictionary3Entity: LiveData<Dictionary3ResultEntity> get() = _dictionary3Entity

    fun getDictionary3(keyword:String){
        viewModelScope.launch {
            var result = network.getDictionary3(keyword)
            if(result.error_code !=1){
                _dictionary3Entity.postValue(result)
            }
        }
    }
}