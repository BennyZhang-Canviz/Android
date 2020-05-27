package com.zsx.dictionary.entity

import com.zsx.dictionary.network.MainNetwork

class SayingViewModelRepository private constructor(private val network: MainNetwork){
    suspend fun getSaying(keyword:String) = network.getSaying(keyword)
}