package com.example.sunflower.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.sunflower.utilities.DATABASE_NAME
import androidx.work.WorkManager

@Database(entities = [GardenPlanting::class, Plant::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase(){
    abstract fun gardenPlantingDao():GardenPlantingDao
    abstract fun plantDao(): PlantDao



    companion object{
        @Volatile private var instance: AppDatabase?=null
        fun getInstance(context: Context): AppDatabase{
            return instance?: synchronized(this){
                instance?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase
        {
            return Room.databaseBuilder(context,AppDatabase::class.java,DATABASE_NAME)
                .addCallback(object: RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request =androidx.work.OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .build()
        }

    }


}