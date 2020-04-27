package com.example.sunflower.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar
import java.util.Calendar.DAY_OF_YEAR

@Entity(tableName = "plants")
class Plant(
    @PrimaryKey @ColumnInfo(name = "id") val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int = 7,
    val imageUrl: String = ""
) {

        /**
         * Determines if the plant should be watered.  Returns true if [since]'s date > date of last
         * watering + watering Interval; false otherwise.
         */
        fun shouldBeWatered(since: Calendar, lastWateringDate: Calendar) =
            since>lastWateringDate.apply {
                add(java.util.Calendar.DAY_OF_YEAR, wateringInterval)
            }


    override fun toString(): String = name
}