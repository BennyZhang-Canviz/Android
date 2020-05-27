package com.zsx.dictionary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zsx.dictionary.entity.SayingEntity
import com.zsx.dictionary.network.MainNetwork
import kotlinx.coroutines.launch
import java.lang.Exception

class SayingViewModel(
    private val network: MainNetwork
) : ViewModel() {
    var sayings: MutableLiveData<List<SayingEntity>> = MutableLiveData()

    fun getSaying(keyword: String) = suspendLoadData {
        var result = network.getSaying(keyword)
        if(result.error_code !=1){
            sayings.postValue(result.result)
        }else{
            //Throw error here.
        }

    }


    private fun suspendLoadData(block: suspend () -> Unit): Unit {
        viewModelScope.launch {
            try {
                block()
            } catch (error: Exception) {
                throw error
            } finally {
            }
        }


    }
}