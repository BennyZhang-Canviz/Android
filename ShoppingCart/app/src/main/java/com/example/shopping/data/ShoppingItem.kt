package com.example.shopping.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShoppingItem")
data class ShoppingItem (
    var name: String,
    var amount: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int?=null
}