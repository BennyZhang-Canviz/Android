package com.zsx.dictionary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zsx.dictionary.network.MainNetwork

class Dictionary1ViewModelFactory ( private val network: MainNetwork):
    ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Dictionary1ViewModel(network) as T
    }
}