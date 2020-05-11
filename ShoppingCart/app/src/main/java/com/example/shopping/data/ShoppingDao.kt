package com.example.shopping.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDao {

    @Query("SELECT * FROM SHOPPINGITEM")
    fun getAll(): LiveData<List<ShoppingItem>>

    @Insert
    suspend fun insert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Update
    suspend fun update(item: ShoppingItem)
}