package com.example.notebook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notebook.data.Note
import com.example.notebook.data.NoteRepository
import kotlinx.coroutines.launch
import java.util.*

class NoteListViewModel(
    var noteRepository: NoteRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    var notes: LiveData<List<Note>> = noteRepository.getNotes()

    fun searchNotes(keyword: String) {
        notes = if(keyword.isEmpty()){
            noteRepository.getNotes()
        }else{
            noteRepository.searchNote(keyword)
        }

    }

    fun deleteAll() =
        viewModelScope.launch {
            noteRepository.deleteAll()
        }


}