package com.zsx.dictionary.util

import com.zsx.dictionary.network.Api
import com.zsx.dictionary.network.MainNetwork
import com.zsx.dictionary.viewmodel.Dictionary1ViewModelFactory
import com.zsx.dictionary.viewmodel.Dictionary2ViewModelFactory
import com.zsx.dictionary.viewmodel.Dictionary3ViewModelFactory
import com.zsx.dictionary.viewmodel.SayingViewModelFactory

object InjectorUtils {

    fun provideSayingViewModelFactory( )
    : SayingViewModelFactory {
        return SayingViewModelFactory(Api.retrofitService)
    }

    fun provideDictionary1ViewModelFactory()
    : Dictionary1ViewModelFactory{
        return Dictionary1ViewModelFactory(Api.retrofitService)
    }

    fun provideDictionary2ViewModelFactory()
            : Dictionary2ViewModelFactory {
        return Dictionary2ViewModelFactory(Api.retrofitService)
    }

    fun provideDictionary3ViewModelFactory()
            : Dictionary3ViewModelFactory {
        return Dictionary3ViewModelFactory(Api.retrofitService)
    }
}