package com.example.shopping.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shopping.data.ShoppingItem
import com.example.shopping.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository) : ViewModel() {

    fun getShoppingItems() = repository.getShoppingItems()

    fun insert(shoppingItem: ShoppingItem) = CoroutineScope(Dispatchers.Main)
        .launch {
            repository.insert(shoppingItem)
        }

    fun delete(shoppingItem: ShoppingItem) = CoroutineScope(Dispatchers.Main)
        .launch {
            repository.delete(shoppingItem)
        }

    fun update(shoppingItem: ShoppingItem) = CoroutineScope(Dispatchers.Main)
        .launch {
            repository.update(shoppingItem)
        }
}