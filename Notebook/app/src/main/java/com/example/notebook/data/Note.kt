package com.example.notebook.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName="table_notes")
data class Note (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val noteId: Long = 0L,

    var title: String,
    var content: String,
    var created: Calendar = Calendar.getInstance(),
    var modified: Calendar = Calendar.getInstance()
):Serializable