package com.example.sunflower.data

class GardenPlantingRepository private constructor(private val gardenPlantingDao: GardenPlantingDao){

    suspend fun createGardenPlanting(plantId: String){
        val gardenPlanting = GardenPlanting(plantId)
        gardenPlantingDao.insertGardenPlanting(gardenPlanting)
    }

    suspend fun removeGardenPlating(gardenPlanting: GardenPlanting){
        gardenPlantingDao.deleteGardenPlanting(gardenPlanting)
    }

    fun isPlanted(plantId:String) = gardenPlantingDao.isPlanted(plantId)

    fun getPlantedGardens() = gardenPlantingDao.getPlantedGardens()

    companion object{
        private var instance: GardenPlantingRepository ? = null
        fun getInstance(gardenPlantingDao: GardenPlantingDao): GardenPlantingRepository{
          return  instance?: synchronized(this){
                instance?: GardenPlantingRepository(gardenPlantingDao ).also { instance = it }
            }
        }
    }

}