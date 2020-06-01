package com.example.notebook.util

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.notebook.data.NoteRepository
import com.example.notebook.data.NotebookDatabase
import com.example.notebook.viewmodel.NoteListViewModelFactory
import com.example.notebook.viewmodel.NoteViewModelFactory

object InjectorUtils {

    fun provideNoteBookViewModelFactory(
        context: Context,
        fragment: Fragment
    ): NoteListViewModelFactory {
        return NoteListViewModelFactory(getNoteRepository(context), fragment)
    }

    fun provideNoteDetailViewModelFactory(id:Long,context: Context) : NoteViewModelFactory{
        return NoteViewModelFactory(id,getNoteRepository(context))
    }

    private fun getNoteRepository(context: Context)
        = NoteRepository.getInstance(
            NotebookDatabase.getInstance(context).noteDao())

}