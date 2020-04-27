package com.example.sunflower.data

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter fun calendarToDatestamp(calendar: Calendar):Long {
        return calendar.timeInMillis
    }
    @TypeConverter fun datestampToCalendar(value:Long) = Calendar.getInstance().apply { timeInMillis = value }
}