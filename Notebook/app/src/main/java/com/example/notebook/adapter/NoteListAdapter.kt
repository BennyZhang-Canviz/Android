package com.example.notebook.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook.R
import com.example.notebook.data.Note
import com.example.notebook.databinding.NoteItemBinding
import com.example.notebook.ui.NotesFragmentDirections

class NoteListAdapter
    : ListAdapter<Note, NoteListAdapter.NoteViewHolder>(NoteDiffCallback()) {

    class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }

    class NoteViewHolder(
        private val binding: NoteItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setCardViewClickListener {view->
                bindClickEvent(view)
            }

            binding.tvTitle.setOnClickListener(){
                bindClickEvent(it)
            }
            binding.tvDescription.setOnClickListener(){
                bindClickEvent(it)
            }
        }

        private fun bindClickEvent(view: View) {
            binding.note?.let { note ->
                navigateToNote(note, view)
            }
        }

        private fun navigateToNote(
            note: Note,
            view: View
        ) {
            val direction =
                NotesFragmentDirections.actionNotesFragmentToNoteDetailFragment(
                    note.noteId
                )
            view.findNavController().navigate(direction)
        }

        fun bind(item:Note){
            binding.apply {
                note = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var note = getItem(position)
        holder.bind(note)
    }

}


