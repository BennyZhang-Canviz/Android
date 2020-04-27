package com.example.sunflower.data

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.example.sunflower.utilities.PLANT_DATA_FILENAME

import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope
import java.io.InputStream
import java.lang.Exception

class SeedDatabaseWorker(appContext: Context, workParameters: WorkerParameters): CoroutineWorker(appContext, workParameters){
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(PLANT_DATA_FILENAME).use{inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Plant>>(){}.type
                    val plantList: List<Plant> =  Gson().fromJson(jsonReader,plantType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.plantDao().insertAll(plantList)
                }
                Result.success()
            }
        }
        catch (ex:Exception){
            Log.e(Tag, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object{
        private val Tag = SeedDatabaseWorker::class.java.simpleName
    }
}