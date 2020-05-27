package com.zsx.dictionary.entity

import com.zsx.dictionary.network.MainNetwork

class Dictionary1ViewModelRepository private constructor(private val network: MainNetwork){
    suspend fun getDictionary1(keyword:String) = network.getDictionary1(keyword)
}