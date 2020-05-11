package com.example.shopping.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopping.data.ShoppingDatabase
import com.example.shopping.repositories.ShoppingRepository

class ShoppingViewModelFactory(private val repository: ShoppingRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T
    }
}

object ViewModelInjector{
 fun getHomeViewModelFactory(context: Context) = ShoppingViewModelFactory(ShoppingRepository(
     ShoppingDatabase.getInstance(context)))
}