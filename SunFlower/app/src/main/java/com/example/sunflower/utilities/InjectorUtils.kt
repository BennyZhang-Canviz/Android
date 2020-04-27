package com.example.sunflower.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.sunflower.data.AppDatabase
import com.example.sunflower.data.PlantRepository
import com.example.sunflower.viewmodels.PlantListViewModelFactory

object InjectorUtils {
    fun providePlantListViewModelFactory(fragment:Fragment): PlantListViewModelFactory {
        val repository: PlantRepository = PlantRepository.getInstance(
            AppDatabase.getInstance(fragment.requireContext()).plantDao()
        )
        return PlantListViewModelFactory(repository,fragment)
    }


}