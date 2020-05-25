package com.example.notebook.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class NoteRepository private constructor(private val noteDao: NoteDao){
    fun getNotes() = noteDao.getAllNotes()
    fun getNoteById(id: Long) = noteDao.getById(id)
    suspend  fun insert(note: Note) = noteDao.insert(note)
    suspend fun update(note:Note) = noteDao.update(note)
    suspend fun delete(note:Note) = noteDao.delete(note)
    suspend fun deleteAll() =  noteDao.deleteAll()

    fun searchNote(keyword: String) = noteDao.searchNotes(keyword)

    companion object{
        @Volatile
        private var instance:NoteRepository? = null

        fun getInstance(noteDao: NoteDao): NoteRepository{
           return instance?: synchronized(this){
                instance?: NoteRepository(noteDao).also { instance = it }
            }
        }
    }


}