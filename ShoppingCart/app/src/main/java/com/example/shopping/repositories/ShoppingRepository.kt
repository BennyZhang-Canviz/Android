package com.example.shopping.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.shopping.data.ShoppingDatabase
import com.example.shopping.data.ShoppingItem

class ShoppingRepository(private val shoppingDatabase: ShoppingDatabase){

    var shoppingDao = shoppingDatabase.getShoppingDao()

    fun getShoppingItems(): LiveData<List<ShoppingItem>>{
        return shoppingDao.getAll()
    }

    suspend fun insert(shoppingItem: ShoppingItem){
        shoppingDao.insert(shoppingItem)
    }

    suspend fun delete(shoppingItem: ShoppingItem){
        shoppingDao.delete(shoppingItem)
    }

    suspend fun update(shoppingItem: ShoppingItem){
        shoppingDao.update(shoppingItem)
    }
}