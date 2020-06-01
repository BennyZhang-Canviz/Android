package com.example.notebook.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.notebook.data.NoteRepository

class NoteListViewModelFactory(
    private val noteRepository: NoteRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
)
    : AbstractSavedStateViewModelFactory(owner,defaultArgs) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
       return NoteListViewModel(noteRepository, handle) as T
    }
}