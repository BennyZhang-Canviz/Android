package com.example.basicapp.util

import android.content.Context
import com.example.basicapp.data.AppDatabase
import com.example.basicapp.data.HomeRepository
import com.example.basicapp.network.Api
import com.example.basicapp.viewmodel.HomeViewModelFactory

object InjectorUtils {

    private val TAG = InjectorUtils::class.java.simpleName

    fun provideHomeViewModelFactory(context: Context):HomeViewModelFactory{
        val repository = getHomeRepository(context)
        return HomeViewModelFactory(repository)
    }

    fun getHomeRepository(context: Context): HomeRepository{
        return HomeRepository.getInstance(Api.retrofitService,
            AppDatabase.getInstance(context.applicationContext).gardenPlantingDao())
    }

}