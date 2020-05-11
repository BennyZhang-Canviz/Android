package com.example.basicapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Plant (
    @ColumnInfo(name = "plant_name")
    val plantName : String,

    @ColumnInfo(name = "plant_data")
    val plantData: Calendar = Calendar.getInstance()
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}