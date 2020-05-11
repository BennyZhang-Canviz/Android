package com.example.shopping.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShoppingItem::class] , version = 1)
abstract class ShoppingDatabase() : RoomDatabase() {
    abstract fun getShoppingDao(): ShoppingDao

    companion object{
        @Volatile
        private var instance : ShoppingDatabase? = null

         fun getInstance(context: Context): ShoppingDatabase {
            return instance
                ?: kotlin.synchronized(this){
                instance
                    ?: buildDatabase(
                        context
                    )
                        .also { instance = it }
            }
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ShoppingDatabase::class.java,"shopping_db")
                .build()
    }
}