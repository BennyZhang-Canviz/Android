package com.zsx.dictionary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zsx.dictionary.network.MainNetwork

class Dictionary2ViewModelFactory (private val network: MainNetwork):
    ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Dictionary2ViewModel(network) as T
    }
}