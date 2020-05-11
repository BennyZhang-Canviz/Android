package com.example.basicapp.data

import androidx.lifecycle.LiveData
import com.example.basicapp.network.MainNetwork
import kotlinx.coroutines.withTimeout

class HomeRepository private constructor(
    private val network: MainNetwork,
    private val gardenPlantingDao: PlantDao
){
    suspend fun createGardenPlanting(plantName:String){
        gardenPlantingDao.insertPlant(Plant(plantName))
    }

    suspend fun removeGardenPlanting(plant:Plant){
        gardenPlantingDao.deletePlant(plant)
    }

    fun getGardenPlants() = gardenPlantingDao.getGardenPlantings()


    suspend fun refreshView():String{
        try {
//            val result = withTimeout(50_00){
//                network.fetchNextTitle()
//            }
            val result = "Apple"
            createGardenPlanting(result)
            return result
        }catch (error:Throwable){
            throw RefreshError("Unable to refresh title", error)
        }
    }

    suspend fun getBanner(): MyResponse<List<Banner>>{
        return withTimeout(50_00){
            network.getBanner()
        }

    }

    class RefreshError(message: String, cause: Throwable): Throwable(message,cause)

    companion object{
        @Volatile
        private var instance: HomeRepository? = null
        fun getInstance(network: MainNetwork, gardenPlantingDao: PlantDao): HomeRepository{
            return instance?: synchronized(this){
                instance?: HomeRepository(network,gardenPlantingDao ).also { instance = it }
            }
        }
    }
}