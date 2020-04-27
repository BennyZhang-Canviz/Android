package com.example.sunflower.viewmodels

import androidx.lifecycle.*
import com.example.sunflower.data.Plant
import com.example.sunflower.data.PlantRepository

class PlantListViewModel internal constructor(
    plantRepository: PlantRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val plants:LiveData<List<Plant>> = getSavedGrowZoneNumber().switchMap {
        if(it == NO_GROW_ZONE){
            plantRepository.getPlants()
        }else{
            plantRepository.getPlantWithGrowZoneNumber(it)
        }
    }

    fun getGrowZoneNumber(num: Int){
        savedStateHandle.set(GROW_ZONE_SAVED_STATE_KEY,num)
    }

    fun clearGrownZoneNumber(){
        savedStateHandle.set(GROW_ZONE_SAVED_STATE_KEY,NO_GROW_ZONE)
    }

    fun getSavedGrowZoneNumber():MutableLiveData<Int>{
        return savedStateHandle.getLiveData(GROW_ZONE_SAVED_STATE_KEY,NO_GROW_ZONE)
    }

    fun isFiltered() = getSavedGrowZoneNumber().value != NO_GROW_ZONE

    companion object{
        private const val NO_GROW_ZONE = -1
        private const val GROW_ZONE_SAVED_STATE_KEY = "GROW_ZONE_SAVED_STATE_KEY"
    }
}