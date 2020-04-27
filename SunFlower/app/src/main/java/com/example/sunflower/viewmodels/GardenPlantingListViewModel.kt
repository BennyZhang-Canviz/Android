package com.example.sunflower.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sunflower.data.GardenPlantingRepository
import com.example.sunflower.data.PlantAndGardenPlantings

class GardenPlantingListViewModel internal constructor(
    gardenPlantingRepository: GardenPlantingRepository
): ViewModel(){
    val plantAndGardenPlantings: LiveData<List<PlantAndGardenPlantings>> = gardenPlantingRepository.getPlantedGardens()
}