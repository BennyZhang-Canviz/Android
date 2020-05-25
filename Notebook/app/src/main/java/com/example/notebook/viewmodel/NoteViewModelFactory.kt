package com.example.notebook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notebook.data.NoteDao
import com.example.notebook.data.NoteRepository

class NoteViewModelFactory(
    var id: Long,
    var noteRepository: NoteRepository
)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteViewModel(id, noteRepository) as T
    }
}