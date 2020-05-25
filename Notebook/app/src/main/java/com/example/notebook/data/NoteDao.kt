package com.example.notebook.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Query("select * from table_notes order by created desc")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("select * from table_notes where id = :id")
    fun getById(id: Long): LiveData<Note>

    @Query(value = "select * from table_notes where title like '%' || :keyword || '%' ")
    fun searchNotes(keyword: String): LiveData<List<Note>>

    @Delete
    suspend fun delete(note: Note)

    @Query("delete from table_notes")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note:Note): Long

    @Update
    suspend fun update(note:Note)
}