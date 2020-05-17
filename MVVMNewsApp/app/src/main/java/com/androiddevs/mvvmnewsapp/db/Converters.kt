package com.androiddevs.mvvmnewsapp.db

import androidx.room.TypeConverter
import com.androiddevs.mvvmnewsapp.model.Source
import java.util.jar.Attributes

class Converters {
    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(str: String): Source{
        return Source(str,str)
    }
}