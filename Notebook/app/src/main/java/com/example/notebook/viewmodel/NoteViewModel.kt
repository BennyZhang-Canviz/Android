package com.example.notebook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notebook.data.Note
import com.example.notebook.data.NoteRepository
import kotlinx.coroutines.launch
import java.util.*

class NoteViewModel (
    var id: Long,
    private val noteRepository: NoteRepository
) : ViewModel() {
      var note:LiveData<Note> =noteRepository.getNoteById(id)

    fun delete(){
        viewModelScope.launch {
            note.value?.let { noteRepository.delete(it) }
        }
    }

    fun update( title: String,  content: String){
        viewModelScope.launch {
            var noteToUpdate = Note(id, title, content, modified = Calendar.getInstance())
            noteRepository.update(noteToUpdate)
        }
    }
    fun create( title: String,  content: String) {
        viewModelScope.launch {
            var noteToUpdate =
                Note(title = title, content = content,created = Calendar.getInstance(), modified = Calendar.getInstance())
            noteRepository.insert(noteToUpdate)
        }
    }
}