package com.example.notebook.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NotebookDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object{
        @Volatile
        var instance: NotebookDatabase?=null

        fun getInstance(context: Context): NotebookDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): NotebookDatabase {
            return Room.databaseBuilder(context, NotebookDatabase::class.java, "NotebookDatabase")
                .addCallback(object :  RoomDatabase.Callback() {

                })
                .build()
        }
    }
}