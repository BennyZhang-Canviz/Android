package com.example.sunflower.data

class PlantRepository private constructor(private val plantDao: PlantDao){
    fun getPlants() = plantDao.getPlants()
    fun getPlant(plantId: String) = plantDao.getPlant(plantId)
    fun getPlantWithGrowZoneNumber(growZoneNumber: Int) = plantDao.getPlantsWithGrowZoneNumber((growZoneNumber))

    companion object{
        @Volatile private var instance : PlantRepository? = null
        fun getInstance(plantDao: PlantDao): PlantRepository{
           return instance ?: synchronized(this){
                instance?: PlantRepository(plantDao).also { instance = it }
            }
        }
    }
}