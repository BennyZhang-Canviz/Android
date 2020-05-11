package com.example.basicapp.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.basicapp.DATABASE_NAME
import java.util.*

@Database(entities = [Plant::class],version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun gardenPlantingDao(): PlantDao

    companion object{
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return instance?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase{
            return Room.databaseBuilder(context,AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                })
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}

class Converters{
    @TypeConverter fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter fun datestampToCalendar(value:Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }

}