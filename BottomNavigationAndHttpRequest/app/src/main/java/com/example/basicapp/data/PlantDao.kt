package com.example.basicapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlantDao {
    @Query("SELECT * FROM PLANT")
    fun getGardenPlantings(): LiveData<List<Plant>>

    @Insert
    suspend fun insertPlant(plant: Plant)

    @Delete
    suspend fun deletePlant(plant: Plant)
}